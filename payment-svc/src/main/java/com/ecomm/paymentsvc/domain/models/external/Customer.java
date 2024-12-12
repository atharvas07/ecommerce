package com.ecomm.paymentsvc.domain.models.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer{

	@JsonProperty("contact")
	private String contact;

	@JsonProperty("name")
	private String name;

	@JsonProperty("email")
	private String email;

	public void setContact(String contact){
		this.contact = contact;
	}

	public String getContact(){
		return contact;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}
}