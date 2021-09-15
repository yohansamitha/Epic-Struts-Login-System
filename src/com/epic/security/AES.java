package com.epic.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class AES {
    private static AES aes;
    private final String algorithm;
    private final Key key;

    private AES(String secret) {
        algorithm = "AES";
        key = generateKey(secret.getBytes());
    }

    public static AES getInstance() {
        return (aes == null) ? aes = new AES("E)H@MbQeThWmZq4t") : aes;
    }

    public Key generateKey(byte[] n) {
        return new SecretKeySpec(n, algorithm);
    }

    public String encrypt(String input) throws Exception {
        System.out.println(input);
        Cipher cipher = Cipher.getInstance(this.algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, this.key);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
    }
}
