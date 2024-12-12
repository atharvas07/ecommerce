package com.ecomm.paymentsvc.domain.impl;

import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.paymentsvc.domain.models.external.RazorPayGenerateLinkResponse;
import com.ecomm.paymentsvc.domain.models.internal.InitiatePaymentClientRequest;
import com.ecomm.paymentsvc.domain.services.PaymentService;
import com.ecomm.paymentsvc.domain.services.RazorPayService;
import com.ecomm.paymentsvc.domain.shared.entities.EcommOrderDetailsProjection;
import com.ecomm.paymentsvc.domain.shared.entities.EcommTransactionsDetail;
import com.ecomm.paymentsvc.domain.shared.repositories.EcommOrderDetailsRepository;
import com.ecomm.paymentsvc.domain.shared.repositories.TransactionsDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {


    RazorPayService razorPayService;
    private final TransactionsDetailsRepository transactionsDetailsRepository;
    private final EcommOrderDetailsRepository ecommOrderDetailsRepository;

    @Autowired
    PaymentServiceImpl(RazorPayService razorPayService, TransactionsDetailsRepository transactionsDetailsRepository, EcommOrderDetailsRepository ecommOrderDetailsRepository) {
        this.razorPayService = razorPayService;
        this.transactionsDetailsRepository = transactionsDetailsRepository;
        this.ecommOrderDetailsRepository = ecommOrderDetailsRepository;
    }

    @Override
    public ResponseEntity<BaseResponse> createPaymentLink(InitiatePaymentClientRequest request, String sessionId) {
        try{
            EcommOrderDetailsProjection orderDetails = ecommOrderDetailsRepository.findByOrderId(request.getOrderId());
            String transactionId = UUID.randomUUID().toString();
            RazorPayGenerateLinkResponse result = razorPayService.createPaymentLink(
                    orderDetails.getUserId().getId(),
                    orderDetails.getUserId().getFirstName() + " " + orderDetails.getUserId().getLastName(),
                    orderDetails.getUserId().getEmail(),
                    orderDetails.getUserId().getMobile().toString(),
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
