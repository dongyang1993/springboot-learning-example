package org.springboot.encrypt.jdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.util.Base64;

public class MD5Util {
    private static final Logger LOGGER = LoggerFactory.getLogger(MD5Util.class);

    public static String md5(byte[] data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(data);
            System.out.println(Integer.toHexString(bytes[0]&0xFF));
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        return null;
    }

    public static void main(String[] args) {
        md5("Hello".getBytes());
    }
}
