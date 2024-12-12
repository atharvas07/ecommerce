package com.ecomm.paymentsvc.domain.models.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Reminders{

	@JsonProperty("status")
	private String status;

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}