package com.ecomm.apigatewaysvc.domain.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptionUtil {

    private static final String ALGORITHM = "AES/ECB/NoPadding";
    private static final int BLOCK_SIZE = 16;
    private static final String SECRET_KEY_256 = "abe874319b324d3480e460b4c47c2142";

    public static String encrypt(String plainText) {
        try {
            byte[] paddedPlaintext = padToBlockSize(plainText.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY_256.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encryptedBytes = cipher.doFinal(paddedPlaintext);
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred during encryption", e);
        }
    }

    public static String decrypt(String encryptedText) {
        try {
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);

            SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY_256.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            return new String(removePadding(decryptedBytes), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred during decryption", e);
        }
    }

    private static byte[] padToBlockSize(byte[] input) {
        int paddedLength = ((input.length + BLOCK_SIZE - 1) / BLOCK_SIZE) * BLOCK_SIZE;
        byte[] padded = new byte[paddedLength];
        System.arraycopy(input, 0, padded, 0, input.length);
        for (int i = input.length; i < paddedLength; i++) {
            padded[i] = (byte) ' ';
        }
        return padded;
    }

    private static byte[] removePadding(byte[] input) {
        int i = input.length - 1;
        while (i >= 0 && input[i] == (byte) ' ') {
            i--;
        }
        byte[] unpadded = new byte[i + 1];
        System.arraycopy(input, 0, unpadded, 0, i + 1);
        return unpadded;
    }
}