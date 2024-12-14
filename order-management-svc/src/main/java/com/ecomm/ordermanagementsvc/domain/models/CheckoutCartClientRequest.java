package com.ecomm.ordermanagementsvc.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckoutCartClientRequest {

    @JsonProperty("address")
    private Address address;

    @JsonProperty("cartId")
    private String cartId;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }
}