package org.springboot.encrypt.jdk.asymmetric;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RsaEncryptUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(RsaEncryptUtil.class);

    public static String algorithm = "RSA";
    public static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwEhipAvdJMAG1CxdiBugGCgBlIhkG1gruOOFXewVHewq6BUUSJcSX9ymFruO5FlxzFFJSdISXOe/Gv8jRHkpnKvSxYcOf0gNF2v8v4aTuB0iuyCjhMnpIccTrzFtCKpjegu1KCcb089vm5jSJ27EoeMYylugk0d98xA4uKWe+o9vSKKRpMHN9uUcWC/1o3Emg9rkel7j+OFB3dx2Fh7mpW39RN+FfkBani80nS7B//LdwpGhzqSfngYeHzkxaO+aPQiDe6BIvy7K/9m9vYYnySfT32UWhSCHaW9u01R12XY62/scrsyLHamZv0C9w/KEwzjmwVbZ7/ffjVF+NXFAnQIDAQAB";
    public static String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDASGKkC90kwAbULF2IG6AYKAGUiGQbWCu444Vd7BUd7CroFRRIlxJf3KYWu47kWXHMUUlJ0hJc578a/yNEeSmcq9LFhw5/SA0Xa/y/hpO4HSK7IKOEyekhxxOvMW0IqmN6C7UoJxvTz2+bmNInbsSh4xjKW6CTR33zEDi4pZ76j29IopGkwc325RxYL/WjcSaD2uR6XuP44UHd3HYWHualbf1E34V+QFqeLzSdLsH/8t3CkaHOpJ+eBh4fOTFo75o9CIN7oEi/Lsr/2b29hifJJ9PfZRaFIIdpb27TVHXZdjrb+xyuzIsdqZm/QL3D8oTDOObBVtnv99+NUX41cUCdAgMBAAECggEAFl5VW1AmAch1AunGV7cfALBmvqi/PI8dRvzw3uQ/8KMwup75JRiuAojgqXRYQyhEnMg4BmZxzG0JRlK3/5c4yAkVE/WKMjRnkXn2hTFareh1yd8aJrB2dn96Qr81N/TabbA++tTR3+oZybgVepDgPxT0JVZTb865UDVq49AglnU1kpy8Sc26t1FKaBaKLjSfNcNgDYa8jAtEa2sh9dVcSlacwUqTEvtGJ+scPgxn1asbjIE6GSdj7+kDDliRm75H6eOpGFof/FHWcs1wuJPyZD8a0A4mB7xEbU17IbIEq4UphxiPPkIMm09m0hPiVQZimqIuvkCnEVlZ3JaphlPkAQKBgQDjApCT4BFw9f5V0yMavF750BIPmAFaHCl54lTuhBlTEEzAwIMF7S5oyqCzvbjOGaEYjIk57APE67Twr+YNnL6JY6rI0d1mgXCH28KpUVkUpa/KXit+gliyGrSTLdNJy49cmzvjgtAy17W0HkbbCkvz25eYYr+wLasPpAaOv2BiPQKBgQDY1oNjW5axLrOtwoidfyc21HhROj8dPoD3TgMvbce4qbdtrcCMivJ6Lk88mmb7FTrldYlklPITqyqqGT4wRznOwvNA11HH99biAuss7xvIdEZffjzhr234+2RYlcBam/FxldTE5Y/+GpHG9LJIgPJUstGcyyKrcBYsYzsmXKId4QKBgQCTrXV0XY27GKxuFL5/hA+dH/i/4Djw7+Ujf1OVHqaC3NWxhws1bciFL1w/0apJmW6tU69p2NnS3yuEcEmOjWt+YTIXFj+bkZUtLI+EKoFy5x5m3cAZup4Mr0bNgEA1f5Exw2LAbLcYfC+ejxu1HwuuBiqmXYcqC2t+70u8zU7cWQKBgDO0OtIXRcBr/WYia8b6SdR2J1ZfCCul+sR9cp79k1ECDv31H2OjwhvGLRirTID0V/3c3+z+4xYzi1HSARUr9qnJrI06ioKfIzrzLTaisUJED3+3rza1SQjEkPHai2pPouvNqVVl08boePgy4swNCzuNBBRaD6NHb9IXu0PzdKihAoGBANkptQLI/rkOcTz8EhStwYJOtCOqunR5VT+80xvPg05mWDT8lV984JMLOT5QHiWHbhmPLwvsBTpTSuOYrkvLFNUxmog4HKn5z0XOlOjG7TCyqm4IoNfFbh/PIOrl6t2jU7ymIAe1SIVFJHObkYxEjh3JxG0512KWJ5ZX4ot1gWVs";

    public static String encrypt(String algorithm, Key key, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] bytes = cipher.doFinal(data);
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            LOGGER.info("", e);
            return null;
        }
    }

    public static byte[] decrypt(String algorithm, Key key, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            LOGGER.info("", e);
            return null;
        }
    }

    public static PublicKey getPublicKey(String algorithm, String key) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key.getBytes(StandardCharsets.UTF_8)));
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePublic(keySpec);
    }

    public static PrivateKey getPrivateKey(String algorithm, String key) throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key.getBytes(StandardCharsets.UTF_8)));
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePrivate(keySpec);
    }


    public static void main(String[] args) throws Exception {
        PublicKey publicKey = getPublicKey(algorithm, RsaEncryptUtil.publicKey);
        String encrypt = encrypt(algorithm, publicKey, "中国@123".getBytes(StandardCharsets.UTF_8));
        System.out.println(encrypt);
        PrivateKey privateKey = getPrivateKey(algorithm, RsaEncryptUtil.privateKey);
        String decrypt = new String(decrypt(algorithm, privateKey, Base64.getDecoder().decode(encrypt)));
        System.out.println(decrypt);
    }
}
