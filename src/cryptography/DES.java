package crypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DES{
 
    private static SecretKeySpec symmetricKey;
    private static byte[] key;
 
    public static void generateKey(String mySecretKey) 
    {
        MessageDigest sha = null;
        try {
            key = mySecretKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 8); 
            symmetricKey = new SecretKeySpec(key, "DES");
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } 
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
    public static String encryption(String plainText, String secretKey) 
    {
        try
        {
            generateKey(secretKey);
            Cipher cipherText = Cipher.getInstance("DES");
            cipherText.init(Cipher.ENCRYPT_MODE, symmetricKey);
            return Base64.getEncoder().encodeToString(cipherText.doFinal(plainText.getBytes("UTF-8")));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
 
    public static String decryption(String encryptedText, String secretKey) 
    {
        try
        {
            generateKey(secretKey);
            Cipher cipherText = Cipher.getInstance("DES");
            cipherText.init(Cipher.DECRYPT_MODE, symmetricKey);
            return new String(cipherText.doFinal(Base64.getDecoder().decode(encryptedText)));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}