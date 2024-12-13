package com.ecomm.paymentsvc.domain.models.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Entity{

	@JsonProperty("amount")
	private int amount;

	@JsonProperty("amount_paid")
	private int amountPaid;

	@JsonProperty("notes")
	private Notes notes;

	@JsonProperty("created_at")
	private long createdAt;

	@JsonProperty("amount_due")
	private int amountDue;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("receipt")
	private Object receipt;

	@JsonProperty("id")
	private String id;

	@JsonProperty("entity")
	private String entity;

	@JsonProperty("offer_id")
	private Object offerId;

	@JsonProperty("attempts")
	private int attempts;

	@JsonProperty("status")
	private String status;

	@JsonProperty("cancelled_at")
	private int cancelledAt;

	@JsonProperty("reminders")
	private Reminders reminders;

	@JsonProperty("reference_id")
	private String referenceId;

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

	@JsonProperty("callback_method")
	private String callbackMethod;

	@JsonProperty("expire_by")
	private int expireBy;

	@JsonProperty("first_min_partial_amount")
	private int firstMinPartialAmount;

	@JsonProperty("whatsapp_link")
	private boolean whatsappLink;

	@JsonProperty("reminder_enable")
	private boolean reminderEnable;

	@JsonProperty("user_id")
	private String userId;

	@JsonProperty("order_id")
	private String orderId;

	@JsonProperty("customer")
	private Customer customer;

	@JsonProperty("fee")
	private int fee;

	@JsonProperty("amount_refunded")
	private int amountRefunded;

	@JsonProperty("bank")
	private String bank;

	@JsonProperty("error_reason")
	private Object errorReason;

	@JsonProperty("acquirer_data")
	private Object acquirerData;

	@JsonProperty("error_description")
	private Object errorDescription;

	@JsonProperty("captured")
	private boolean captured;

	@JsonProperty("contact")
	private String contact;

	@JsonProperty("base_amount")
	private int baseAmount;

	@JsonProperty("invoice_id")
	private Object invoiceId;

	@JsonProperty("international")
	private boolean international;

	@JsonProperty("email")
	private String email;

	@JsonProperty("amount_transferred")
	private int amountTransferred;

	@JsonProperty("refund_status")
	private Object refundStatus;

	@JsonProperty("wallet")
	private String wallet;

	@JsonProperty("method")
	private String method;

	@JsonProperty("error_source")
	private Object errorSource;

	@JsonProperty("error_step")
	private Object errorStep;

	@JsonProperty("vpa")
	private String vpa;

	@JsonProperty("tax")
	private int tax;

	@JsonProperty("card_id")
	private Object cardId;

	@JsonProperty("error_code")
	private Object errorCode;

	@JsonProperty("fee_bearer")
	private String feeBearer;

	@JsonProperty("card")
	private String card;

	public void setAmount(int amount){
		this.amount = amount;
	}

	public int getAmount(){
		return amount;
	}

	public void setAmountPaid(int amountPaid){
		this.amountPaid = amountPaid;
	}

	public int getAmountPaid(){
		return amountPaid;
	}

	public void setNotes(Notes notes){
		this.notes = notes;
	}

	public Notes getNotes(){
		return notes;
	}

	public void setCreatedAt(long createdAt){
		this.createdAt = createdAt;
	}

	public long getCreatedAt(){
		return createdAt;
	}

	public void setAmountDue(int amountDue){
		this.amountDue = amountDue;
	}

	public int getAmountDue(){
		return amountDue;
	}

	public void setCurrency(String currency){
		this.currency = currency;
	}

	public String getCurrency(){
		return currency;
	}

	public void setReceipt(Object receipt){
		this.receipt = receipt;
	}

	public Object getReceipt(){
		return receipt;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setEntity(String entity){
		this.entity = entity;
	}

	public String getEntity(){
		return entity;
	}

	public void setOfferId(Object offerId){
		this.offerId = offerId;
	}

	public Object getOfferId(){
		return offerId;
	}

	public void setAttempts(int attempts){
		this.attempts = attempts;
	}

	public int getAttempts(){
		return attempts;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setCancelledAt(int cancelledAt){
		this.cancelledAt = cancelledAt;
	}

	public int getCancelledAt(){
		return cancelledAt;
	}

	public void setReminders(Reminders reminders){
		this.reminders = reminders;
	}

	public Reminders getReminders(){
		return reminders;
	}

	public void setReferenceId(String referenceId){
		this.referenceId = referenceId;
	}

	public String getReferenceId(){
		return referenceId;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setExpiredAt(int expiredAt){
		this.expiredAt = expiredAt;
	}

	public int getExpiredAt(){
		return expiredAt;
	}

	public void setNotify(Notify notify){
		this.notify = notify;
	}

	public Notify getNotify(){
		return notify;
	}

	public void setShortUrl(String shortUrl){
		this.shortUrl = shortUrl;
	}

	public String getShortUrl(){
		return shortUrl;
	}

	public void setCallbackUrl(String callbackUrl){
		this.callbackUrl = callbackUrl;
	}

	public String getCallbackUrl(){
		return callbackUrl;
	}

	public void setUpdatedAt(int updatedAt){
		this.updatedAt = updatedAt;
	}

	public long getUpdatedAt(){
		return updatedAt;
	}

	public void setUpiLink(boolean upiLink){
		this.upiLink = upiLink;
	}

	public boolean isUpiLink(){
		return upiLink;
	}

	public void setAcceptPartial(boolean acceptPartial){
		this.acceptPartial = acceptPartial;
	}

	public boolean isAcceptPartial(){
		return acceptPartial;
	}

	public void setCallbackMethod(String callbackMethod){
		this.callbackMethod = callbackMethod;
	}

	public String getCallbackMethod(){
		return callbackMethod;
	}

	public void setExpireBy(int expireBy){
		this.expireBy = expireBy;
	}

	public int getExpireBy(){
		return expireBy;
	}

	public void setFirstMinPartialAmount(int firstMinPartialAmount){
		this.firstMinPartialAmount = firstMinPartialAmount;
	}

	public int getFirstMinPartialAmount(){
		return firstMinPartialAmount;
	}

	public void setWhatsappLink(boolean whatsappLink){
		this.whatsappLink = whatsappLink;
	}

	public boolean isWhatsappLink(){
		return whatsappLink;
	}

	public void setReminderEnable(boolean reminderEnable){
		this.reminderEnable = reminderEnable;
	}

	public boolean isReminderEnable(){
		return reminderEnable;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return orderId;
	}

	public void setCustomer(Customer customer){
		this.customer = customer;
	}

	public Customer getCustomer(){
		return customer;
	}

	public void setFee(int fee){
		this.fee = fee;
	}

	public int getFee(){
		return fee;
	}

	public void setAmountRefunded(int amountRefunded){
		this.amountRefunded = amountRefunded;
	}

	public int getAmountRefunded(){
		return amountRefunded;
	}

	public void setBank(String bank){
		this.bank = bank;
	}

	public String getBank(){
		return bank;
	}

	public void setErrorReason(Object errorReason){
		this.errorReason = errorReason;
	}

	public Object getErrorReason(){
		return errorReason;
	}

	public Object getAcquirerData() {
		return acquirerData;
	}

	public void setAcquirerData(Object acquirerData) {
		this.acquirerData = acquirerData;
	}

	public void setErrorDescription(Object errorDescription){
		this.errorDescription = errorDescription;
	}

	public Object getErrorDescription(){
		return errorDescription;
	}

	public void setCaptured(boolean captured){
		this.captured = captured;
	}

	public boolean isCaptured(){
		return captured;
	}

	public void setContact(String contact){
		this.contact = contact;
	}

	public String getContact(){
		return contact;
	}

	public void setBaseAmount(int baseAmount){
		this.baseAmount = baseAmount;
	}

	public int getBaseAmount(){
		return baseAmount;
	}

	public void setInvoiceId(Object invoiceId){
		this.invoiceId = invoiceId;
	}

	public Object getInvoiceId(){
		return invoiceId;
	}

	public void setInternational(boolean international){
		this.international = international;
	}

	public boolean isInternational(){
		return international;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setAmountTransferred(int amountTransferred){
		this.amountTransferred = amountTransferred;
	}

	public int getAmountTransferred(){
		return amountTransferred;
	}

	public void setRefundStatus(Object refundStatus){
		this.refundStatus = refundStatus;
	}

	public Object getRefundStatus(){
		return refundStatus;
	}

	public void setWallet(String wallet){
		this.wallet = wallet;
	}

	public String getWallet(){
		return wallet;
	}

	public void setMethod(String method){
		this.method = method;
	}

	public String getMethod(){
		return method;
	}

	public void setErrorSource(Object errorSource){
		this.errorSource = errorSource;
	}

	public Object getErrorSource(){
		return errorSource;
	}

	public void setErrorStep(Object errorStep){
		this.errorStep = errorStep;
	}

	public Object getErrorStep(){
		return errorStep;
	}

	public void setVpa(String vpa){
		this.vpa = vpa;
	}

	public String getVpa(){
		return vpa;
	}

	public void setTax(int tax){
		this.tax = tax;
	}

	public int getTax(){
		return tax;
	}

	public void setCardId(Object cardId){
		this.cardId = cardId;
	}

	public String getCardId(){
		return (String) cardId;
	}

	public void setErrorCode(Object errorCode){
		this.errorCode = errorCode;
	}

	public Object getErrorCode(){
		return errorCode;
	}

	public void setFeeBearer(String feeBearer){
		this.feeBearer = feeBearer;
	}

	public String getFeeBearer(){
		return feeBearer;
	}

	public void setCard(String card){
		this.card = card;
	}

	public String getCard(){
		return card;
	}



}