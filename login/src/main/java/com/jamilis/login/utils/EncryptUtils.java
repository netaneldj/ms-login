package com.jamilis.login.utils;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Component;

@Component
public class EncryptUtils {
    private static final String ENCRYPT_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String SECRET_KEY_ALGORITHM = "AES";
    private static final String ENCODING = "UTF-8";
    private static final String SHA = "SHA-1";
    private static SecretKeySpec secretKeySpec;
    private static byte[] key;

    /**
     *
     * @param myKey
     */
    private static void setKey(final String myKey) {
        try {
            MessageDigest sha = null;
            key = myKey.getBytes(ENCODING);
            sha = MessageDigest.getInstance(SHA);
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKeySpec = new SecretKeySpec(key, SECRET_KEY_ALGORITHM);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Password set key exception: {}",
                    e.getMessage()));
        }
    }

    /**
     *
     * @param strToEncrypt
     * @param secret
     * @return
     */
    public static String encrypt(final String strToEncrypt, final String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance(ENCRYPT_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(strToEncrypt.getBytes(ENCODING)));
        } catch (Exception e){
            throw new RuntimeException(String.format("Password encrypt exception: {}", e.getMessage()));
        }
    }

    /**
     *
     * @param strToDecrypt
     * @param secret
     * @return
     */
    public static String decrypt(final String strToDecrypt, final String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance(ENCRYPT_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            return new String(cipher.doFinal(Base64.getDecoder()
                    .decode(strToDecrypt)));
        } catch (Exception e) {
            throw new RuntimeException(String.format("Password decrypt exception: {}", e.getMessage()));
        }
    }
}
