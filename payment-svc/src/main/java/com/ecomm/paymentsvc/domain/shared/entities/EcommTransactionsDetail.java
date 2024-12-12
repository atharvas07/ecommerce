package com.ecomm.paymentsvc.domain.shared.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;


@Entity
@Table(name = "ecomm_transactions_details")
public class EcommTransactionsDetail {
    @Id
    @Size(max = 60)
    @Column(name = "ID", nullable = false, length = 60)
    private String id = UUID.randomUUID().toString();

    @Size(max = 100)
    @NotNull
    @Column(name = "PAYMENT_REF_NUMBER", nullable = false, length = 100)
    private String paymentRefNumber;

    @Size(max = 16)
    @Column(name = "CUSTOMER_MOBILE", length = 16)
    private String customerMobile;

    @Size(max = 60)
    @Column(name = "USER_ID", length = 60)
    private String userId;

    @Size(max = 100)
    @Column(name = "CUSTOMER_EMAIL", length = 100)
    private String customerEmail;

    @Size(max = 100)
    @Column(name = "CUSTOMER_NAME", length = 100)
    private String customerName;

    @Column(name = "AMOUNT")
    private Double amount;

    @Size(max = 45)
    @Column(name = "STATUS", length = 45)
    private String status;

    @Size(max = 60)
    @Column(name = "ORDER_ID", length = 60)
    private String orderId;

    @Column(name = "FEES")
    private Double fees;

    @Column(name = "TAX")
    private Double tax;

    @Size(max = 45)
    @Column(name = "METHOD", length = 45)
    private String method;

    @Size(max = 45)
    @Column(name = "BANK", length = 45)
    private String bank;

    @Size(max = 45)
    @Column(name = "CARD_ID", length = 45)
    private String cardId;

    @Size(max = 45)
    @Column(name = "VPA", length = 45)
    private String vpa;

    @Size(max = 45)
    @Column(name = "CARD", length = 45)
    private String card;

    @Size(max = 45)
    @Column(name = "WALLET")
    private String wallet;

    @Column(name = "ACQUIRER_DATA")
    private String acquirerData;

    @Column(name = "TXN_TIME")
    private Long txnTime;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPaymentRefNumber() {
        return paymentRefNumber;
    }
    public void setPaymentRefNumber(String paymentRefNumber) {
        this.paymentRefNumber = paymentRefNumber;
    }
    public String getCustomerMobile() {
        return customerMobile;
    }
    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Double getFees() {
        return fees;
    }
    public void setFees(Double fees) {
        this.fees = fees;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public Long getTxnTime() {
        return txnTime;
    }
    public void setTxnTime(Long txnTime) {
        this.txnTime = txnTime;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getBank() {
        return bank;
    }
    public void setBank(String bank) {
        this.bank = bank;
    }
    public String getCardId() {
        return cardId;
    }
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
    public String getVpa() {
        return vpa;
    }
    public void setVpa(String vpa) {
        this.vpa = vpa;
    }
    public String getCard() {
        return card;
    }
    public void setCard(String card) {
        this.card = card;
    }
    public String getAcquirerData() {
        return acquirerData;
    }
    public void setAcquirerData(String acquirerData) {
        this.acquirerData = acquirerData;
    }
    public String getWallet() {
        return wallet;
    }
    public void setWallet(String wallet) {
        this.wallet = wallet;
    }
    public Double getTax() {
        return tax;
    }
    public void setTax(Double tax) {
        this.tax = tax;
    }
}