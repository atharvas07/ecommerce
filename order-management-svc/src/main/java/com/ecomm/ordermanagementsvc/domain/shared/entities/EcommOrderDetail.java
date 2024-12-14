package com.ecomm.ordermanagementsvc.domain.shared.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "ecomm_order_details")
public class EcommOrderDetail {
    @Id
    @Size(max = 45)
    @Column(name = "ORDER_ID", nullable = false, length = 45)
    private String orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
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
    @JoinColumn(name = "BILLING_ADDRESS", insertable = false, updatable = false)
    private EcommUserAddressDetail billingAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHIPPING_ADDRESS", insertable = false, updatable = false)
    private EcommUserAddressDetail shippingAddress;

    @Column(name = "BILLING_ADDRESS")
    private String billingAddressId;

    @Column(name = "SHIPPING_ADDRESS")
    private String shippingAddressId;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "TRANSACTION_ID")
    private String transactionId;

    @OneToMany(mappedBy = "order")
    private Set<EcommPurchaseProductDetail> purchaseProductDetails = new LinkedHashSet<>();

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setBillingAddressId(String billingAddressId) {
        this.billingAddressId = billingAddressId;
    }

    public void setShippingAddressId(String shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public Double getAmount() {
        return amount;
    }

    public Set<EcommPurchaseProductDetail> getPurchaseProductDetails() {
        return purchaseProductDetails;
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