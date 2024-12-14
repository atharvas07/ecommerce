package com.ecomm.ordermanagementsvc.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {

    @JsonProperty("shipping")
    private Shipping shipping;

    @JsonProperty("billing")
    private Billing billing;

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }
}