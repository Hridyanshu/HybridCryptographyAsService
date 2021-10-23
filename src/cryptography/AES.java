package cryptography;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

class AES{
 
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
            symmetricKey = new SecretKeySpec(key, "AES");
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } 
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
    static String encryption(String plainText, String secretKey) 
    {
        try
        {
            generateKey(secretKey);
            Cipher cipherText = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipherText.init(Cipher.ENCRYPT_MODE, symmetricKey);
            return Base64.getEncoder().encodeToString(cipherText.doFinal(plainText.getBytes("UTF-8")));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
 
    static String decryption(String encryptedText, String secretKey) 
    {
        try
        {
            generateKey(secretKey);
            Cipher cipherText = Cipher.getInstance("AES/ECB/PKCS5PADDING");
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