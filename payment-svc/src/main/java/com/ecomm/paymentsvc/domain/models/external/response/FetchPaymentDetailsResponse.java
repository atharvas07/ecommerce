package com.ecomm.paymentsvc.domain.models.external.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FetchPaymentDetailsResponse {

    @JsonProperty("notes")
    private Notes notes;

    @JsonProperty("fee")
    private int fee;

    @JsonProperty("description")
    private String description;

    @JsonProperty("created_at")
    private long createdAt;

    @JsonProperty("amount_refunded")
    private int amountRefunded;

    @JsonProperty("bank")
    private String bank;

    @JsonProperty("error_reason")
    private Object errorReason;

    @JsonProperty("error_description")
    private Object errorDescription;

    @JsonProperty("acquirer_data")
    private Object acquirerData;

    @JsonProperty("captured")
    private boolean captured;

    @JsonProperty("contact")
    private String contact;

    @JsonProperty("invoice_id")
    private Object invoiceId;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("id")
    private String id;

    @JsonProperty("international")
    private boolean international;

    @JsonProperty("email")
    private String email;

    @JsonProperty("amount")
    private int amount;

    @JsonProperty("refund_status")
    private Object refundStatus;

    @JsonProperty("wallet")
    private String wallet;

    @JsonProperty("method")
    private String method;

    @JsonProperty("vpa")
    private String vpa;

    @JsonProperty("error_source")
    private Object errorSource;

    @JsonProperty("error_step")
    private Object errorStep;

    @JsonProperty("tax")
    private int tax;

    @JsonProperty("card_id")
    private String cardId;

    @JsonProperty("upi")
    private Upi upi;

    @JsonProperty("error_code")
    private Object errorCode;

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("entity")
    private String entity;

    @JsonProperty("status")
    private String status;

    @JsonProperty("card")
    private String card;

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public int getAmountRefunded() {
        return amountRefunded;
    }

    public void setAmountRefunded(int amountRefunded) {
        this.amountRefunded = amountRefunded;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Object getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(Object errorReason) {
        this.errorReason = errorReason;
    }

    public Object getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(Object errorDescription) {
        this.errorDescription = errorDescription;
    }

    public Object getAcquirerData() {
        return acquirerData;
    }

    public void setAcquirerData(Object acquirerData) {
        this.acquirerData = acquirerData;
    }

    public boolean isCaptured() {
        return captured;
    }

    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Object getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Object invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isInternational() {
        return international;
    }

    public void setInternational(boolean international) {
        this.international = international;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Object getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Object refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getVpa() {
        return vpa;
    }

    public void setVpa(String vpa) {
        this.vpa = vpa;
    }

    public Object getErrorSource() {
        return errorSource;
    }

    public void setErrorSource(Object errorSource) {
        this.errorSource = errorSource;
    }

    public Object getErrorStep() {
        return errorStep;
    }

    public void setErrorStep(Object errorStep) {
        this.errorStep = errorStep;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Upi getUpi() {
        return upi;
    }

    public void setUpi(Upi upi) {
        this.upi = upi;
    }

    public Object getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Object errorCode) {
        this.errorCode = errorCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}