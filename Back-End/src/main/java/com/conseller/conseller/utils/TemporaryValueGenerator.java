package com.conseller.conseller.utils;

import java.security.SecureRandom;

public class TemporaryValueGenerator {

    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final int LENGTH = 12;

    public static String generateTemporaryValue() {
        StringBuilder password = new StringBuilder(LENGTH);

        for (int i = 0; i < LENGTH; i++) {
            password.append(CHARS.charAt(SECURE_RANDOM.nextInt(CHARS.length())));
        }

        return password.toString();
    }
}
