package cryptography;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

class RC4 {

    private static SecretKeySpec symmetricKey;
    private static byte[] key;

    static void generateKey(String mySecretKey) 
    {
        MessageDigest sha = null;
        try {
            key = mySecretKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); 
            symmetricKey = new SecretKeySpec(key, "ARCFOUR");
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } 
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    static String encrypt(String plaintext, String secretKey) {
        try
        {
            generateKey(secretKey);
            Cipher rc4 = Cipher.getInstance("ARCFOUR");
            rc4.init(Cipher.ENCRYPT_MODE, symmetricKey);
            byte[] plaintextBytes = plaintext.getBytes();
            byte[] ciphertextBytes = rc4.doFinal(plaintextBytes);
            return Base64.getEncoder().encodeToString(ciphertextBytes);
        } 
        catch (Exception e) 
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    static String decrypt(String ciphertext, String secretKey) {
        try
        {
            generateKey(secretKey);
            Cipher rc4 = Cipher.getInstance("ARCFOUR");
            rc4.init(Cipher.DECRYPT_MODE, symmetricKey);
            return new String(rc4.doFinal(Base64.getDecoder().decode(ciphertext)));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}