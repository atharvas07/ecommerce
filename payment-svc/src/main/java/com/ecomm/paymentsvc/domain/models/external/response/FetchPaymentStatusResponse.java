package com.ecomm.paymentsvc.domain.models.external.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FetchPaymentStatusResponse {

    @JsonProperty("cancelled_at")
    private int cancelledAt;

    @JsonProperty("reminders")
    private Reminders reminders;

    @JsonProperty("amount_paid")
    private int amountPaid;

    @JsonProperty("notes")
    private Notes notes;

    @JsonProperty("reference_id")
    private String referenceId;

    @JsonProperty("payments")
    private List<PaymentsItem> payments;

    @JsonProperty("created_at")
    private int createdAt;

    @JsonProperty("description")
    private String description;

    @JsonProperty("expired_at")
    private int expiredAt;

    @JsonProperty("notify")
    private Notify notify;

    @JsonProperty("short_url")
    private String shortUrl;

    @JsonProperty("callback_url")
    private String callbackUrl;

    @JsonProperty("updated_at")
    private int updatedAt;

    @JsonProperty("upi_link")
    private boolean upiLink;

    @JsonProperty("accept_partial")
    private boolean acceptPartial;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("id")
    private String id;

    @JsonProperty("callback_method")
    private String callbackMethod;

    @JsonProperty("expire_by")
    private int expireBy;

    @JsonProperty("first_min_partial_amount")
    private int firstMinPartialAmount;

    @JsonProperty("whatsapp_link")
    private boolean whatsappLink;

    @JsonProperty("amount")
    private int amount;

    @JsonProperty("reminder_enable")
    private boolean reminderEnable;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("customer")
    private Customer customer;

    @JsonProperty("status")
    private String status;

    public int getCancelledAt() {
        return cancelledAt;
    }

    public void setCancelledAt(int cancelledAt) {
        this.cancelledAt = cancelledAt;
    }

    public Reminders getReminders() {
        return reminders;
    }

    public void setReminders(Reminders reminders) {
        this.reminders = reminders;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public List<PaymentsItem> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentsItem> payments) {
        this.payments = payments;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(int expiredAt) {
        this.expiredAt = expiredAt;
    }

    public Notify getNotify() {
        return notify;
    }

    public void setNotify(Notify notify) {
        this.notify = notify;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public int getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(int updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isUpiLink() {
        return upiLink;
    }

    public void setUpiLink(boolean upiLink) {
        this.upiLink = upiLink;
    }

    public boolean isAcceptPartial() {
        return acceptPartial;
    }

    public void setAcceptPartial(boolean acceptPartial) {
        this.acceptPartial = acceptPartial;
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

    public String getCallbackMethod() {
        return callbackMethod;
    }

    public void setCallbackMethod(String callbackMethod) {
        this.callbackMethod = callbackMethod;
    }

    public int getExpireBy() {
        return expireBy;
    }

    public void setExpireBy(int expireBy) {
        this.expireBy = expireBy;
    }

    public int getFirstMinPartialAmount() {
        return firstMinPartialAmount;
    }

    public void setFirstMinPartialAmount(int firstMinPartialAmount) {
        this.firstMinPartialAmount = firstMinPartialAmount;
    }

    public boolean isWhatsappLink() {
        return whatsappLink;
    }

    public void setWhatsappLink(boolean whatsappLink) {
        this.whatsappLink = whatsappLink;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isReminderEnable() {
        return reminderEnable;
    }

    public void setReminderEnable(boolean reminderEnable) {
        this.reminderEnable = reminderEnable;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}