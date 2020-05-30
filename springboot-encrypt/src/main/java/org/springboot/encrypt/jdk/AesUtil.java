package org.springboot.encrypt.jdk;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class AesUtil {

    private String keyAlgorithm = "AES";
    private String algorithm = "AES/CBC/PKCS5Padding";
    private int keySize = 128;
    private String iv = "";




    public static String encrypt() {
        return null;
    }

    public void initKey() {

    }

    public static String buildKey()  throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES/CBC/PKCS5Padding");
        return null;
    }

    public static class AesBuilder {
        private String keyAlgorithm = "AES";
        private String algorithm = "AES/CBC/PKCS5Padding";
        private int keySize = 128;
        private String iv = "";

        public AesBuilder keyAlgorithm(String keyAlgorithm) {
            this.keyAlgorithm = keyAlgorithm;
            return this;
        }
    }
}
