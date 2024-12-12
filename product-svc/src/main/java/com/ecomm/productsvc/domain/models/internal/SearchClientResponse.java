package com.ecomm.productsvc.domain.models.internal;

import com.ecomm.productsvc.domain.models.external.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SearchClientResponse {

    @JsonProperty("products")
    private List<Product> products;

    @JsonProperty("totalElements")
    private long totalElements;

    @JsonProperty("totalPages")
    private long totalPages;

    public List<Product> getProducts() {
        return products;
    }
    public long getTotalElements() {
        return totalElements;
    }
    public long getTotalPages() {
        return totalPages;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }
}
