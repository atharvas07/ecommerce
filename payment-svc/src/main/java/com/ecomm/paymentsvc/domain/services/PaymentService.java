package com.ecomm.paymentsvc.domain.services;


import com.ecomm.mircrosvclib.models.BaseResponse;
import com.ecomm.paymentsvc.domain.models.internal.InitiatePaymentClientRequest;
import org.springframework.http.ResponseEntity;

public interface PaymentService {
    ResponseEntity<BaseResponse> createPaymentLink(InitiatePaymentClientRequest request, String sessionId);
}
