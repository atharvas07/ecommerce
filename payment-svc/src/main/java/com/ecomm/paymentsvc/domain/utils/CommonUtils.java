package com.ecomm.paymentsvc.domain.utils;

public class CommonUtils {

    public static String generateOrderId() {
        return String.valueOf("OR"+System.currentTimeMillis());
    }
}
