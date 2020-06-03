package org.springboot.encrypt.jdk.symmetric.demo;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.security.Security;
import java.util.Base64;

/**
 * @Author DongYang
 * @Description
 * @Date 2020/4/26 18:20
 * 3DES规范：
 * IV必须为8字节长度
 * Key必须为24字节长度(大于24字节的，会自动截取24字节)
 **/
public final class TripleDesUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(TripleDesUtil.class);

    public static final String KEY_ALGORITHM = "DESede";
    public static final String ALGORITHM1 = "DESede/CBC/PKCS5Padding";
    public static final String ALGORITHM2 = "DESede/CBC/PKCS7Padding";

    static {
        //因为JAVA不支持PKCS7Padding 所以需要从外部加入支持组件
        Security.addProvider(new BouncyCastleProvider());
    }

    public static byte[] encode(String keyStr, byte[] source, String iv, String algorithm) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            Key key = buildKey(keyStr);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
            return cipher.doFinal(source);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        return new byte[0];
    }

    public static String encodeToBase64(String key, byte[] source, String iv, String algorithm) {
        return Base64.getEncoder().encodeToString(TripleDesUtil.encode(key, source, iv, algorithm));
    }


    public static byte[] decode(String keyStr, byte[] source, String iv, String algorithm) throws Exception {
        Cipher cipher = Cipher.getInstance(algorithm);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
        Key key = buildKey(keyStr);
        cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
        return cipher.doFinal(source);
    }


    public static byte[] decodeFromBase64(String key, String source, String iv, String algorithm) throws Exception {
        return decode(key, Base64.getDecoder().decode(source), iv, algorithm);
    }

    public static Key buildKey(String key) throws Exception {
        /**
         * key的长度必须为24字节或者长度大于24字节的字符串
         */
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        DESedeKeySpec dks = new DESedeKeySpec(key.getBytes());
        return keyFactory.generateSecret(dks);
    }

    public static void main(String[] args) throws Exception {
        String iv = "foaocuen";
        String key = "f510b8737344cddbca1c8564k9418293846123974123473";
        String data = "中国ABCabc1234";

        String encode = encodeToBase64(key, data.getBytes(), iv, ALGORITHM2);
        String decode = new String(decodeFromBase64(key, encode, iv, ALGORITHM2));
        System.out.println(encode);
        System.out.println(decode);
    }
}
