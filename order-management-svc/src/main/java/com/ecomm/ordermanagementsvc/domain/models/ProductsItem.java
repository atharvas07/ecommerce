package com.ecomm.ordermanagementsvc.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductsItem {

    @JsonProperty("quantity")
    private String quantity;

    @JsonProperty("id")
    private Long id;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}