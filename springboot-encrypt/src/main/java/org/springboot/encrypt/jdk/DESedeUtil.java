package org.springboot.encrypt.jdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @Author DongYang
 * @Description key长度为24字节
 * iv长度为8字节
 * @Date 2020/5/30 17:49
 **/
public class DESedeUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(DESedeUtil.class);

    public static byte[] encrypt(String keyStr, String ivStr, byte[] data, String transformation, String algorithm) {
        try {
            Cipher cipher = Cipher.getInstance(transformation);
            SecretKey keySpec = new SecretKeySpec(keyStr.getBytes(StandardCharsets.UTF_8), algorithm);
            /**
             * 采用密钥工厂创建密钥
             * SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
             * 下面这两行效果是等同的--创建密钥材料
             * DESKeySpec desKeySpec = new DESKeySpec(keyStr.getBytes(StandardCharsets.UTF_8));
             * SecretKeySpec desKeySpec = new SecretKeySpec(keyStr.getBytes(StandardCharsets.UTF_8), algorithm);
             *
             * SecretKey keySpec = keyFactory.generateSecret(desKeySpec);
             */
            if (ivStr != null && ivStr.length() > 0) {
                IvParameterSpec ivSpec = new IvParameterSpec(ivStr.getBytes(StandardCharsets.UTF_8));
                cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            } else {
                cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            }
            return cipher.doFinal(data);
        } catch (Exception e) {
            LOGGER.error("", e);
            return new byte[0];
        }
    }

    public static String encryptBase64(String keyStr, String ivStr, byte[] data, String transformation, String algorithm) {
        return Base64.getEncoder().encodeToString(encrypt(keyStr, ivStr, data, transformation, algorithm));
    }

    public static byte[] decrypt(String keyStr, String ivStr, byte[] data, String transformation, String algorithm) {
        try {
            Cipher cipher = Cipher.getInstance(transformation);
            SecretKeySpec keySpec = new SecretKeySpec(keyStr.getBytes(StandardCharsets.UTF_8), algorithm);
            if (ivStr != null && ivStr.length() > 0) {
                IvParameterSpec ivSpec = new IvParameterSpec(ivStr.getBytes(StandardCharsets.UTF_8));
                cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            } else {
                cipher.init(Cipher.DECRYPT_MODE, keySpec);
            }
            return cipher.doFinal(data);
        } catch (Exception e) {
            LOGGER.error("", e);
            return new byte[0];
        }
    }

    public static byte[] decryptBase64(String keyStr, String ivStr, byte[] data, String transformation, String algorithm) {
        byte[] tmp = Base64.getDecoder().decode(data);
        return decrypt(keyStr, ivStr, tmp, transformation, algorithm);
    }

    public static void main(String[] args) {
        String keyStr = "abcdefghabcdefghabcdefgh";
        String ivStr = "12345678";
        String transformation = "DESede/CBC/PKCS5Padding";
        String algorithm = "DESede";
        String data = "中国@1234";
        String base64 = encryptBase64(keyStr, ivStr, data.getBytes(StandardCharsets.UTF_8), transformation, algorithm);
        System.out.println(base64);
        byte[] decrypt = decryptBase64(keyStr, ivStr, base64.getBytes(StandardCharsets.UTF_8), transformation, algorithm);
        System.out.println(new String(decrypt));
    }
}
