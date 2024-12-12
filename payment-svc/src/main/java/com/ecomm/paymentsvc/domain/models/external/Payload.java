package com.ecomm.paymentsvc.domain.models.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Payload{

	@JsonProperty("payment")
	private Payment payment;

	@JsonProperty("payment_link")
	private PaymentLink paymentLink;

	@JsonProperty("order")
	private Order order;

	public void setPayment(Payment payment){
		this.payment = payment;
	}

	public Payment getPayment(){
		return payment;
	}

	public void setPaymentLink(PaymentLink paymentLink){
		this.paymentLink = paymentLink;
	}

	public PaymentLink getPaymentLink(){
		return paymentLink;
	}

	public void setOrder(Order order){
		this.order = order;
	}

	public Order getOrder(){
		return order;
	}
}