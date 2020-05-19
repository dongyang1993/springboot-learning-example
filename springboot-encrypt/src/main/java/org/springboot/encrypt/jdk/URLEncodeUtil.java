package org.springboot.encrypt.jdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * @Author DongYang
 * @Description
 * @Date 2020/5/19 22:28
 **/
public final class URLEncodeUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(URLEncodeUtil.class);

    public static String encode(String data, Charset charset) {
        try {
            return URLEncoder.encode(data, charset.name());
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("URLEncode异常:{}", data, e);
        }
        return data;
    }

    public static String decode(String data, Charset charset) {
        try {
            return URLDecoder.decode(data, charset.name());
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("URLDecode异常:{}", data, e);
        }
        return data;
    }
}
