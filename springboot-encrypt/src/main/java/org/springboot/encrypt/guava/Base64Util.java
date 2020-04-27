package org.springboot.encrypt.guava;

import com.google.common.io.BaseEncoding;

import java.nio.charset.Charset;

/**
 * @Author DongYang
 * @Description Base64工具类
 * @Date 2020/4/26 11:47
 **/
public final class Base64Util {

    public static BaseEncoding base64 = BaseEncoding.base64();
    public static BaseEncoding base64Url = BaseEncoding.base64Url();


    public static String encode(byte[] bytes) {
        return base64.encode(bytes);
    }

    public static String encode(String source, Charset charset) {
        return base64.encode(source.getBytes(charset));
    }

    public static byte[] decode(String source) {
        return base64.decode(source);
    }

    public static String decode(String source, Charset charset) {
        return new String(base64.decode(source), charset);
    }


    /**
     * Base64 Url Safe
     *
     * @param
     * @return
     */

    public static String encodeUrlSafe(byte[] bytes) {
        return base64Url.encode(bytes);
    }

    public static String encodeUrlSafe(String source, Charset charset) {
        return base64Url.encode(source.getBytes(charset));
    }


    public static String decodeUrlSafe(String source, Charset charset) {
        return new String(base64Url.decode(source), charset);
    }

    public static void main(String[] args) {
        String gbk = new String("中国 China".getBytes(), Charset.forName("GBK"));
        String encode = encode(gbk, Charset.forName("GBK"));
        String decode = decode(encode, Charset.forName("GBK"));
        System.out.println("");
    }
}
