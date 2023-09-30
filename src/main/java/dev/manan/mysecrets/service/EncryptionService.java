package dev.manan.mysecrets.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class EncryptionService {

    private static final String ENCRYPTION_ALGORITHM = "AES";
    private SecretKey secretKey;
    private Cipher cipher;

    public EncryptionService(@Value("${encryption.secret.key}") String key) {
        try {
            byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
            secretKey = new SecretKeySpec(keyBytes, "AES"); // Assign to the class-level field
            cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("Error initializing encryption service", e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Error initializing encryption service", e);
        }
    }

    public String encrypt(String data) {
        try {
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException("Error encrypting data", e);
        }
    }

    public String decrypt(String encryptedData) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException("Error decrypting data", e);
        }
    }
}