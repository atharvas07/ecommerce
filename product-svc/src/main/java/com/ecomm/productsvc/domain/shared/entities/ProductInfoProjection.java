package com.ecomm.productsvc.domain.shared.entities;


public interface ProductInfoProjection {
    Long getId();
    String getTitle();
    Double getPrice();
    String getDescription();
    CategoryInfo getCategory();
    Double getRating();
    int getRatingCount();
    String getImageUrl();
    Integer getQuantity();

    interface CategoryInfo {
        Long getId();
        String getCategoryName();
    }
}
