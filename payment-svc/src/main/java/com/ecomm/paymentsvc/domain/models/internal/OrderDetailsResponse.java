package com.ecomm.paymentsvc.domain.models.internal;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrderDetailsResponse{

	@JsonProperty("amount")
	private double amount;

	@JsonProperty("orderId")
	private String orderId;

	@JsonProperty("shippingAddress")
	private ShippingAddress shippingAddress;

	@JsonProperty("purchaseProductDetails")
	private List<PurchaseProductDetailsItem> purchaseProductDetails;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("billingAddress")
	private BillingAddress billingAddress;

	@JsonProperty("user")
	private User user;

	@JsonProperty("status")
	private String status;

	public void setAmount(double amount){
		this.amount = amount;
	}

	public double getAmount(){
		return amount;
	}

	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getOrderId(){
		return orderId;
	}

	public void setShippingAddress(ShippingAddress shippingAddress){
		this.shippingAddress = shippingAddress;
	}

	public ShippingAddress getShippingAddress(){
		return shippingAddress;
	}

	public void setPurchaseProductDetails(List<PurchaseProductDetailsItem> purchaseProductDetails){
		this.purchaseProductDetails = purchaseProductDetails;
	}

	public List<PurchaseProductDetailsItem> getPurchaseProductDetails(){
		return purchaseProductDetails;
	}

	public void setCurrency(String currency){
		this.currency = currency;
	}

	public String getCurrency(){
		return currency;
	}

	public void setBillingAddress(BillingAddress billingAddress){
		this.billingAddress = billingAddress;
	}

	public BillingAddress getBillingAddress(){
		return billingAddress;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}