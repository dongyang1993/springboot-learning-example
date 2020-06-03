package org.springboot.encrypt.jdk.hash;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springboot.encrypt.jdk.HexUtil;

import java.security.MessageDigest;

public class HashUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HashUtil.class);

    private static final String MD5 = "MD5";

    private static final String SHA1 = "SHA-1";
    private static final String SHA224 = "SHA-224";
    private static final String SHA256 = "SHA-256";
    private static final String SHA384 = "SHA-384";
    private static final String SHA512 = "SHA-512";

    public static byte[] sha1(byte[] data, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            return md.digest(data);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        return new byte[0];
    }

    public static String sha1Hex(byte[] data, String algorithm) {
        return HexUtil.byteToHex(sha1(data, algorithm));
    }

    public static void main(String[] args) {
        System.out.println(sha1Hex("Hello".getBytes(), HashUtil.SHA224));
    }
}
