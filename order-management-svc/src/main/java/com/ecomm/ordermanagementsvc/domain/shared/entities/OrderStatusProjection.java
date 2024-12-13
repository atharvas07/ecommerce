package com.ecomm.ordermanagementsvc.domain.shared.entities;


import java.time.Instant;

public interface OrderStatusProjection {
    String getStatus();

    String getStatusDescription();

    Instant getCreatedAt();
}
