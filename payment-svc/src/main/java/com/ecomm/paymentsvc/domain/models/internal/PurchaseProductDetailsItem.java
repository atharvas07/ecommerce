package com.ecomm.paymentsvc.domain.models.internal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PurchaseProductDetailsItem{

	@JsonProperty("amount")
	private Object amount;

	@JsonProperty("product")
	private Product product;

	@JsonProperty("quantity")
	private int quantity;

	@JsonProperty("currentStatus")
	private String currentStatus;

	public void setAmount(Object amount){
		this.amount = amount;
	}

	public Object getAmount(){
		return amount;
	}

	public void setProduct(Product product){
		this.product = product;
	}

	public Product getProduct(){
		return product;
	}

	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setCurrentStatus(String currentStatus){
		this.currentStatus = currentStatus;
	}

	public String getCurrentStatus(){
		return currentStatus;
	}
}