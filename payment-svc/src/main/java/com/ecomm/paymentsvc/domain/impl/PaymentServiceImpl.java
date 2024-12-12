package com.ecomm.paymentsvc.domain.impl;

import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.paymentsvc.domain.models.external.RazorPayGenerateLinkResponse;
import com.ecomm.paymentsvc.domain.services.PaymentService;
import com.ecomm.paymentsvc.domain.services.RazorPayService;
import com.ecomm.paymentsvc.domain.shared.entities.EcommTransactionsDetail;
import com.ecomm.paymentsvc.domain.shared.repositories.TransactionsDetailsRepository;
import com.ecomm.paymentsvc.domain.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {


    RazorPayService razorPayService;
    private final TransactionsDetailsRepository transactionsDetailsRepository;

    @Autowired
    PaymentServiceImpl(RazorPayService razorPayService, TransactionsDetailsRepository transactionsDetailsRepository) {
        this.razorPayService = razorPayService;
        this.transactionsDetailsRepository = transactionsDetailsRepository;
    }

    @Override
    public ResponseEntity<BaseResponse> createPaymentLink() {
        try{
            String orderId = CommonUtils.generateOrderId();
            String transactionId = UUID.randomUUID().toString();
            RazorPayGenerateLinkResponse result = razorPayService.createPaymentLink(
                    "asda",
                    "Atharva Sarode",
                    "atharvasarode77@gmail.com",
                    "9372717797",
                    200.0,
                    orderId,
                    transactionId
            );

            EcommTransactionsDetail transactionDetails = getTransactionDetails(orderId, result);
            transactionsDetailsRepository.save(transactionDetails);

            return BaseResponse.getSuccessResponse(result).toResponseEntity();

        }catch (Exception e){
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
        return transactionDetails;
    }
}
