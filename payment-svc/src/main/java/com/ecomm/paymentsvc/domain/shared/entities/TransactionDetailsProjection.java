package com.ecomm.paymentsvc.domain.shared.entities;

public interface TransactionDetailsProjection {
    String getId();

    String getPaymentRefNumber();

    double getAmount();

    String getStatus();

    String getMethod();

    String getUserId();
}
