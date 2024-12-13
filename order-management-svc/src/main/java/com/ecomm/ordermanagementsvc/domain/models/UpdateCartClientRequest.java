package com.ecomm.ordermanagementsvc.domain.models;

import com.ecomm.ordermanagementsvc.domain.shared.entities.MongoCartDetails;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UpdateCartClientRequest {

    @JsonProperty("action")
    private String action;

    @JsonProperty("products")
    private List<MongoCartDetails.Product> products;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<MongoCartDetails.Product> getProducts() {
        return products;
    }

    public void setProducts(List<MongoCartDetails.Product> products) {
        this.products = products;
    }
}