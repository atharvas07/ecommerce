package com.ecomm.paymentsvc.domain.models.external.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Notify {

    @JsonProperty("whatsapp")
    private boolean whatsapp;

    @JsonProperty("sms")
    private boolean sms;

    @JsonProperty("email")
    private boolean email;

    public boolean isWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(boolean whatsapp) {
        this.whatsapp = whatsapp;
    }

    public boolean isSms() {
        return sms;
    }

    public void setSms(boolean sms) {
        this.sms = sms;
    }

    public boolean isEmail() {
        return email;
    }

    public void setEmail(boolean email) {
        this.email = email;
    }
}