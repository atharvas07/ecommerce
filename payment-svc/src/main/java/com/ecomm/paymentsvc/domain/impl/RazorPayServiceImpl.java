package com.ecomm.paymentsvc.domain.impl;

import com.ecomm.mircrosvclib.utils.JsonUtils;
import com.ecomm.paymentsvc.domain.models.external.Entity;
import com.ecomm.paymentsvc.domain.models.external.RazorPayGenerateLinkResponse;
import com.ecomm.paymentsvc.domain.models.external.RazorPayWebhookRequest;
import com.ecomm.paymentsvc.domain.services.RazorPayService;
import com.ecomm.paymentsvc.domain.shared.entities.EcommTransactionsDetail;
import com.ecomm.paymentsvc.domain.shared.repositories.TransactionsDetailsRepository;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.apache.logging.log4j.LogManager.getLogger;

@Service
public class RazorPayServiceImpl implements RazorPayService {

    @Value( "${razorpay.api.key}")
    private String apiKey;

    @Value( "${razorpay.api.secret}")
    private String apiSecret;

    @Value( "${razorpay.api.callback.url}")
    private String callbackUrl;

    private static final String CURRENCY_INR = "INR";
    private static final String CALLBACK_METHOD = "get";

    private final Logger logger = getLogger(RazorPayServiceImpl.class);

    private final TransactionsDetailsRepository transactionsDetailsRepository;

    @Autowired
    public RazorPayServiceImpl(TransactionsDetailsRepository transactionsDetailsRepository) {
        this.transactionsDetailsRepository = transactionsDetailsRepository;
    }

    @Override
    public RazorPayGenerateLinkResponse createPaymentLink(
            String userId, String customerName, String customerEmail,
            String customerContact, double amount, String orderId, String transactionId) {
        try {
            RazorpayClient client = new RazorpayClient(apiKey, apiSecret);
            Map<String, Object> paymentOptions = buildPaymentLinkOptions(userId, customerName, customerEmail, customerContact, amount, orderId, transactionId);

            PaymentLink paymentLink = client.paymentLink.create(new JSONObject(paymentOptions));

            logger.info(paymentLink.toJson());

            return JsonUtils.getBeanByJson(paymentLink.toString(), RazorPayGenerateLinkResponse.class);
        } catch (RazorpayException e) {
            logger.error(e.getMessage());
            throw new RuntimeException("Error occurred while creating payment link: " + e.getMessage(), e);
        }
    }

    @Override
    public void webhook(RazorPayWebhookRequest request) {
        try{
            Entity paymentEntity = request.getPayload().getPayment().getEntity();
            Optional<EcommTransactionsDetail> existingTransaction = transactionsDetailsRepository.findById(paymentEntity.getNotes().getTransactionId());
            existingTransaction.ifPresent(transaction -> {
                transaction.setStatus(paymentEntity.getStatus());
                transaction.setFees((double) (paymentEntity.getFee() / 100));
                transaction.setMethod(paymentEntity.getMethod());
                transaction.setTxnTime(paymentEntity.getUpdatedAt());
                transaction.setVpa(paymentEntity.getVpa());
                transaction.setBank(paymentEntity.getBank());
                transaction.setCard(paymentEntity.getCard());
                transaction.setCardId(paymentEntity.getCardId());
                transaction.setAcquirerData(JsonUtils.getJSON(paymentEntity.getAcquirerData()));
                transaction.setWallet(paymentEntity.getWallet());
                transaction.setTax((double) paymentEntity.getTax()/ 100);
                transactionsDetailsRepository.save(transaction);
            });
        } catch (Exception e){
            logger.error(e.getMessage());
            logger.error(e.getStackTrace());
        }
    }


    private Map<String, Object> buildPaymentLinkOptions(
            String userId, String customerName, String customerEmail,
            String customerContact, double amount, String orderId, String transactionId) {
        Map<String, Object> options = new HashMap<>();
        options.put("amount", (int) (amount * 100));
        options.put("currency", CURRENCY_INR);
        options.put("description", "Payment for Order " + orderId);

        options.put("customer", new JSONObject()
                .put("name", customerName)
                .put("email", customerEmail)
                .put("contact", customerContact)
        );

        options.put("notify", new JSONObject()
                .put("sms", false)
                .put("email", true)
        );
        options.put("callback_url", callbackUrl);
        options.put("callback_method", CALLBACK_METHOD);

        options.put("notes", new JSONObject()
                .put("order_id", orderId)
                .put("user_id", userId)
                .put("transaction_id", transactionId));

        return options;
    }
}
