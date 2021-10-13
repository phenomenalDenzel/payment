package com.qds.ulinzi.security;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA512HashingStrategy {

    public static String generateHash(String passwordToDigest) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(passwordToDigest.getBytes(StandardCharsets.UTF_8));

        byte[] digest = md.digest();
        return Hex.encodeHexString(digest);
    }
}
