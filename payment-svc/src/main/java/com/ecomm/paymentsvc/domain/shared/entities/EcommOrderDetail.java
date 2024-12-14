package com.ecomm.paymentsvc.domain.shared.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "ecomm_order_details")
public class EcommOrderDetail {
    @Id
    @Size(max = 45)
    @Column(name = "ORDER_ID", nullable = false, length = 45)
    private String orderId;

    @Size(max = 45)
    @Column(name = "STATUS", length = 45)
    private String status;

    @Column(name = "AMOUNT")
    private Double amount;

    @Size(max = 45)
    @Column(name = "CURRENCY", length = 45)
    private String currency;

    @Column(name = "TRANSACTION_ID")
    private String transactionId;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "CREATED_AT")
    private Instant createdAt;

    @Size(max = 45)
    @Column(name = "PAYMENT_STATUS", length = 45)
    private String paymentStatus;

    public String getTransactionId() {
        return transactionId;
    }

    public Double getAmount() {
        return amount;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getCurrency() {
        return currency;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getStatus() {
        return status;
    }
}