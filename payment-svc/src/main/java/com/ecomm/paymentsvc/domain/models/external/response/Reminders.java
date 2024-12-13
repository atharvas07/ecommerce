package com.ecomm.paymentsvc.domain.models.external.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Reminders {

    @JsonProperty("status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}