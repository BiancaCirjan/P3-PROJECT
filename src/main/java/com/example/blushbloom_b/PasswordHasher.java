package com.example.blushbloom_b;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {

    public static String hashPassword(String password) {
        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");


            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));


            return bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error while hashing password: " + e.getMessage());
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void main(String[] args) {
        String password = "1234";
        String hashedPassword = hashPassword(password);

        System.out.println("Original Password: " + password);
        System.out.println("Hashed Password: " + hashedPassword);
    }
}
