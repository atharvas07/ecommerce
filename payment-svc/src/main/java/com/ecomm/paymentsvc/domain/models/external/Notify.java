package com.ecomm.paymentsvc.domain.models.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Notify{

	@JsonProperty("whatsapp")
	private boolean whatsapp;

	@JsonProperty("sms")
	private boolean sms;

	@JsonProperty("email")
	private boolean email;

	public void setWhatsapp(boolean whatsapp){
		this.whatsapp = whatsapp;
	}

	public boolean isWhatsapp(){
		return whatsapp;
	}

	public void setSms(boolean sms){
		this.sms = sms;
	}

	public boolean isSms(){
		return sms;
	}

	public void setEmail(boolean email){
		this.email = email;
	}

	public boolean isEmail(){
		return email;
	}
}