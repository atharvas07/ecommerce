package com.ecomm.ordermanagementsvc.domain.shared.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ecomm_order_details")
public class EcommOrderDetail {
    @Id
    @Size(max = 45)
    @Column(name = "ORDER_ID", nullable = false, length = 45)
    private String orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private EcommUserDetail user;

    @Size(max = 45)
    @Column(name = "STATUS", length = 45)
    private String status;

    @Column(name = "AMOUNT")
    private Double amount;

    @Size(max = 45)
    @Column(name = "CURRENCY", length = 45)
    private String currency;

    @Size(max = 45)
    @Column(name = "PAYMENT_STATUS", length = 45)
    private String paymentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BILLING_ADDRESS")
    private EcommUserAddressDetail billingAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHIPPING_ADDRESS")
    private EcommUserAddressDetail shippingAddress;

    public Double getAmount() {
        return amount;
    }

    public EcommUserAddressDetail getBillingAddress() {
        return billingAddress;
    }
    public EcommUserAddressDetail getShippingAddress() {
        return shippingAddress;
    }
    public String getOrderId() {
        return orderId;
    }
    public EcommUserDetail getUser() {
        return user;
    }
    public String getStatus() {
        return status;
    }
    public String getCurrency() {
        return currency;
    }
    public String getPaymentStatus() {
        return paymentStatus;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public void setBillingAddress(EcommUserAddressDetail billingAddress) {
        this.billingAddress = billingAddress;
    }
    public void setShippingAddress(EcommUserAddressDetail shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public void setUser(EcommUserDetail user) {
        this.user = user;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}