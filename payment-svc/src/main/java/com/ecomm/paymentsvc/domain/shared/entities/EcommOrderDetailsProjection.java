package com.ecomm.paymentsvc.domain.shared.entities;

import java.time.Instant;

public interface EcommOrderDetailsProjection {
    String getOrderId();
    String getStatus();
    Double getAmount();
    String getCurrency();
    Instant getCreatedAt();
    String getPaymentStatus();

    EcommUserDetailProjection getUserId();
    interface EcommUserDetailProjection {
        String getId();
        String getEmail();
        String getFirstName();
        String getLastName();
        Long getMobile();
    }
}


