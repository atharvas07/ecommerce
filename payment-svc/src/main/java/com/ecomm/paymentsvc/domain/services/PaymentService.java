package com.ecomm.paymentsvc.domain.services;


import com.ecomm.mircrosvclib.models.BaseResponse;
import org.springframework.http.ResponseEntity;

public interface PaymentService {
    ResponseEntity<BaseResponse> createPaymentLink(String orderId, String sessionId);
}
