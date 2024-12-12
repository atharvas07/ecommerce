package com.ecomm.paymentsvc.domain.models.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AcquirerData{

	@JsonProperty("bank_transaction_id")
	private String bankTransactionId;

	public void setBankTransactionId(String bankTransactionId){
		this.bankTransactionId = bankTransactionId;
	}

	public String getBankTransactionId(){
		return bankTransactionId;
	}
}