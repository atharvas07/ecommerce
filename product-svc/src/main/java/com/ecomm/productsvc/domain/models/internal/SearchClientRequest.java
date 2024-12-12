package com.ecomm.productsvc.domain.models.internal;


import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchClientRequest {

    @JsonProperty("searchTerm")
    private String searchTerm;

    @JsonProperty("pageNumber")
    private int pageNumber;

    @JsonProperty("pageSize")
    private int pageSize;

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
}
