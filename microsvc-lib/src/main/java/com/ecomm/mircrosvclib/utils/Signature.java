package com.ecomm.mircrosvclib.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.SignatureException;
import java.util.HexFormat;

public class Signature {

    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

    public static String calculateRFC2104HMAC(String data, String secret) throws java.security.SignatureException {
        String result;
        try {

            SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), HMAC_SHA256_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes());
            result = HexFormat.of().formatHex(rawHmac).toLowerCase();

        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }
        return result;
    }
}