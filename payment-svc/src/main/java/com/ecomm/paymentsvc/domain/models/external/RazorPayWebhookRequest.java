package com.ecomm.paymentsvc.domain.models.external;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RazorPayWebhookRequest{

	@JsonProperty("contains")
	private List<String> contains;

	@JsonProperty("account_id")
	private String accountId;

	@JsonProperty("payload")
	private Payload payload;

	@JsonProperty("created_at")
	private int createdAt;

	@JsonProperty("event")
	private String event;

	@JsonProperty("entity")
	private String entity;

	public void setContains(List<String> contains){
		this.contains = contains;
	}

	public List<String> getContains(){
		return contains;
	}

	public void setAccountId(String accountId){
		this.accountId = accountId;
	}

	public String getAccountId(){
		return accountId;
	}

	public void setPayload(Payload payload){
		this.payload = payload;
	}

	public Payload getPayload(){
		return payload;
	}

	public void setCreatedAt(int createdAt){
		this.createdAt = createdAt;
	}

	public int getCreatedAt(){
		return createdAt;
	}

	public void setEvent(String event){
		this.event = event;
	}

	public String getEvent(){
		return event;
	}

	public void setEntity(String entity){
		this.entity = entity;
	}

	public String getEntity(){
		return entity;
	}
}