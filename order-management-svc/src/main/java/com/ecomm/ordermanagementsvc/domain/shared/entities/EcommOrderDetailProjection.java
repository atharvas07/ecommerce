package com.ecomm.ordermanagementsvc.domain.shared.entities;

import java.util.Set;

public interface EcommOrderDetailProjection {
    String getOrderId();
    String getStatus();
    Double getAmount();
    String getCurrency();

    UserProjection getUser();
    AddressProjection getBillingAddress();
    AddressProjection getShippingAddress();
    Set<PurchaseProductsProjection> getPurchaseProductDetails();

    interface PurchaseProductsProjection {
        EcommOrderDetailProjection.ProductProjection getProduct();
        Integer getQuantity();
        String getCurrentStatus();
        Double getAmount();
    }
    interface UserProjection {
        String getFirstName();
        String getLastName();
        String getEmail();
        String getMobile();
    }

    interface AddressProjection {
        String getAddressLine1();
        String getAddressLine2();
        String getPinCode();
        String getState();
        String getCity();
    }

    interface ProductProjection {
        String getId();
        String getTitle();
    }
}