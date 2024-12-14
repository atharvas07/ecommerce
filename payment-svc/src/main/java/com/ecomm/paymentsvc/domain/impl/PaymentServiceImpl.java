package com.ecomm.paymentsvc.domain.impl;

import com.ecomm.mircrosvclib.exception.CustomException;
import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.mircrosvclib.utils.EnumUtils;
import com.ecomm.mircrosvclib.utils.JsonUtils;
import com.ecomm.paymentsvc.domain.models.external.RazorPayGenerateLinkResponse;
import com.ecomm.paymentsvc.domain.models.internal.OrderDetailsResponse;
import com.ecomm.paymentsvc.domain.services.PaymentService;
import com.ecomm.paymentsvc.domain.services.RazorPayService;
import com.ecomm.paymentsvc.domain.shared.entities.EcommOrderDetail;
import com.ecomm.paymentsvc.domain.shared.entities.EcommTransactionsDetail;
import com.ecomm.paymentsvc.domain.shared.entities.TransactionDetailsProjection;
import com.ecomm.paymentsvc.domain.shared.proxy.OrderManagementServiceProxy;
import com.ecomm.paymentsvc.domain.shared.repositories.EcommOrderDetailsRepository;
import com.ecomm.paymentsvc.domain.shared.repositories.TransactionsDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {


    private final EcommOrderDetailsRepository ecommOrderDetailsRepository;
    RazorPayService razorPayService;
    private final TransactionsDetailsRepository transactionsDetailsRepository;
    private final OrderManagementServiceProxy orderManagementServiceProxy;
    @Autowired
    PaymentServiceImpl(RazorPayService razorPayService, TransactionsDetailsRepository transactionsDetailsRepository, OrderManagementServiceProxy orderManagementServiceProxy, EcommOrderDetailsRepository ecommOrderDetailsRepository) {
        this.razorPayService = razorPayService;
        this.transactionsDetailsRepository = transactionsDetailsRepository;
        this.orderManagementServiceProxy = orderManagementServiceProxy;
        this.ecommOrderDetailsRepository = ecommOrderDetailsRepository;
    }

    @Override
    public ResponseEntity<BaseResponse> createPaymentLink(String userId, String orderId, String sessionId) {
        try{
            OrderDetailsResponse orderDetails = JsonUtils.getBeanByObject(Objects.requireNonNull(orderManagementServiceProxy.getOrderDetails(userId, orderId).getBody()).getRespMsg(), OrderDetailsResponse.class);
            String transactionId = UUID.randomUUID().toString();
            if (EnumUtils.ProductDeliveryStatus.valueOf(orderDetails.getStatus()).getSequenceNumber() > 1 && EnumUtils.ProductDeliveryStatus.valueOf(orderDetails.getStatus()).getSequenceNumber() != 10)
                throw new Exception("Payment already done.");
            RazorPayGenerateLinkResponse result = razorPayService.createPaymentLink(
                    userId,
                    orderDetails.getUser().getFirstName() + " " + orderDetails.getUser().getLastName(),
                    orderDetails.getUser().getEmail(),
                    orderDetails.getUser().getMobile(),
                    orderDetails.getAmount(),
                    orderDetails.getOrderId(),
                    transactionId
            );
            EcommTransactionsDetail transactionDetails = getTransactionDetails(orderDetails.getOrderId(), result);
            transactionsDetailsRepository.save(transactionDetails);
            return BaseResponse.getSuccessResponse(result.getShortUrl()).toResponseEntity();

        }catch (Exception e){
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public String createPaymentLink(String userId, String customerName, String customerEmail,
                                    String customerContact, double amount, String orderId) {
        try {
            String transactionId = UUID.randomUUID().toString();
            RazorPayGenerateLinkResponse result = razorPayService.createPaymentLink(
                    userId,
                    customerName,
                    customerEmail,
                    customerContact,
                    amount,
                    orderId,
                    transactionId
            );
            EcommTransactionsDetail transactionDetails = getTransactionDetails(orderId, result);
            transactionsDetailsRepository.save(transactionDetails);
            return result.getShortUrl();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ResponseEntity<BaseResponse> getPaymentDetails(String orderId, String userId) {
        try {
            Optional<EcommOrderDetail> orderDetail = ecommOrderDetailsRepository.findById(orderId);
            if (orderDetail.isEmpty()) throw new CustomException("order not found");
            TransactionDetailsProjection transactionDetails = transactionsDetailsRepository.findByIdAndUserId(orderDetail.get().getTransactionId(), userId);
            if (transactionDetails == null) throw new CustomException("transaction not found");

            return BaseResponse.getSuccessResponse(transactionDetails).toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }


    private static EcommTransactionsDetail getTransactionDetails(String orderId, RazorPayGenerateLinkResponse result) {
        EcommTransactionsDetail transactionDetails = new EcommTransactionsDetail();
        transactionDetails.setOrderId(orderId);
        transactionDetails.setPaymentRefNumber(result.getId());
        transactionDetails.setAmount((double) (result.getAmount() / 100));
        transactionDetails.setStatus(result.getStatus());
        transactionDetails.setCustomerMobile(result.getCustomer().getContact());
        transactionDetails.setCustomerEmail(result.getCustomer().getEmail());
        transactionDetails.setCustomerName(result.getCustomer().getName());
        transactionDetails.setId(result.getNotes().getTransactionId());
        transactionDetails.setUserId(result.getNotes().getUserId());
        transactionDetails.setPaymentRefNumber(result.getId());
        return transactionDetails;
    }
}
