package com.moniotring.portal.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private static final String SECRET_KEY = "1234567890123456"; // 16 bytes key for AES encryption

    // Method to encrypt the password
    public String encryptPassword(String plainPassword) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encryptedPassword = cipher.doFinal(plainPassword.getBytes());
            return Base64.getEncoder().encodeToString(encryptedPassword);
        } catch (Exception e) {
            // Log the exception (optional)
            e.printStackTrace();
            return null; // Return null or a default value to handle errors gracefully
        }
    }

    // Method to decrypt the password
    public String decryptPassword(String encryptedPassword) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] decodedPassword = Base64.getDecoder().decode(encryptedPassword);
            byte[] decryptedPassword = cipher.doFinal(decodedPassword);
            return new String(decryptedPassword);
        } catch (Exception e) {
            // Log the exception (optional)
            e.printStackTrace();
            return null; // Return null or a default value to handle errors gracefully
        }
    }

    // Verify if the entered password matches the encrypted password (after decryption)
    public boolean matchesPassword(String plainPassword, String encryptedPassword) {
        try {
            String decryptedPassword = decryptPassword(encryptedPassword);
            return plainPassword.equals(decryptedPassword);
        } catch (Exception e) {
            // Log the exception (optional)
            e.printStackTrace();
            return false; // Return false if there's an error during decryption
        }
    }
}
