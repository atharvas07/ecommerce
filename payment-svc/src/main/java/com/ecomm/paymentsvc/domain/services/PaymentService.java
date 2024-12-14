package com.ecomm.paymentsvc.domain.services;


import com.ecomm.mircrosvclib.models.BaseResponse;
import org.springframework.http.ResponseEntity;

public interface PaymentService {
    ResponseEntity<BaseResponse> createPaymentLink(String userId, String orderId, String sessionId);

    String createPaymentLink(String userId, String customerName, String customerEmail,
                             String customerContact, double amount, String orderId);

    ResponseEntity<BaseResponse> getPaymentDetails(String orderId, String userId);
}
