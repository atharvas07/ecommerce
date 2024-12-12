package com.ecomm.paymentsvc.domain.models.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentLink{

	@JsonProperty("entity")
	private Entity entity;

	public void setEntity(Entity entity){
		this.entity = entity;
	}

	public Entity getEntity(){
		return entity;
	}
}