package com.ecomm.paymentsvc.domain.models.internal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product{

	@JsonProperty("id")
	private String id;

	@JsonProperty("title")
	private String title;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}
}