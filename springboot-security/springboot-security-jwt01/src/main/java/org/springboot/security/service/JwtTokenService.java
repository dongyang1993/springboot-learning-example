package org.springboot.security.service;

import com.nimbusds.jose.JOSEException;
import org.springboot.security.entity.PayloadDTO;

import java.text.ParseException;

public interface JwtTokenService {

    /**
     * 使用HMAC算法生成token
     * @param payload
     * @param secret
     * @return
     */
    String generateTokenByHMAC(String payload, String secret) throws JOSEException;

    /**
     * 使用HMAC算法校验token
     */
    PayloadDTO verifyTokenByHMAC(String token, String secret) throws ParseException, JOSEException;

    /**
     * 获取默认payload
     */
    PayloadDTO getDefaultPayload();
}
