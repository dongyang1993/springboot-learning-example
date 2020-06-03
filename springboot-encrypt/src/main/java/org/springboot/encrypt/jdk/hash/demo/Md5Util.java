package org.springboot.encrypt.jdk.hash.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springboot.encrypt.jdk.HexUtil;

import java.security.MessageDigest;

public class Md5Util {
    private static final Logger LOGGER = LoggerFactory.getLogger(Md5Util.class);

    public static byte[] md5(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return md.digest(data);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        return new byte[0];
    }

    public static String md5Hex(byte[] data) {
        return HexUtil.byteToHex(md5(data));
    }

    public static void main(String[] args) {
        System.out.println(md5Hex("Hello".getBytes()));
    }
}
