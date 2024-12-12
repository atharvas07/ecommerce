package com.ecomm.paymentsvc.domain.shared.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;


@Entity
@Table(name = "ecomm_order_details")
public class EcommOrderDetails {
    @Id
    @Size(max = 45)
    @Column(name = "ORDER_ID", nullable = false, length = 45)
    private String orderId;

    @Size(max = 45)
    @Column(name = "STATUS", length = 45)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private EcommUserDetail userId;

    @Column(name = "AMOUNT")
    private Double amount;

    @Size(max = 45)
    @Column(name = "CURRENCY", length = 45)
    private String currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRANSACTION_ID")
    private EcommTransactionsDetail transaction;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "CREATED_AT")
    private Instant createdAt;

    @Size(max = 45)
    @Column(name = "PAYMENT_STATUS", length = 45)
    private String paymentStatus;

    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public EcommTransactionsDetail getTransaction() {
        return transaction;
    }
    public void setTransaction(EcommTransactionsDetail transaction) {
        this.transaction = transaction;
    }
    public Instant getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
    public String getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    public EcommUserDetail getUserId() {
        return userId;
    }
    public void setUserId(EcommUserDetail userId) {
        this.userId = userId;
    }

}