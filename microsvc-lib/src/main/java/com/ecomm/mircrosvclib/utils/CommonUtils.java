package com.ecomm.mircrosvclib.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CommonUtils {

    /**
     * Encodes a given string using MD5 hashing.
     *
     * @param input the string to be hashed
     * @return the MD5 hash of the input string in hexadecimal format
     */
    public static String encodeMD5(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }

    public static String generateOrderId() {
        return String.valueOf("OR"+System.currentTimeMillis());
    }
}
