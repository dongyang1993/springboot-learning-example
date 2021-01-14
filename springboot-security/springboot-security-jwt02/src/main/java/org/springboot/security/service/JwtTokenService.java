package org.springboot.security.service;

import com.nimbusds.jose.JOSEException;
import org.springboot.security.entity.PayloadDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.ParseException;

/**
 * JWT 的三个部分依次如下。
 * Header（头部）
 * Payload（负载）
 * Signature（签名）
 * 即: Header.Payload.Signature
 *
 * Payload官方规定了7个属性供选用，也可以自定义属性
 * iss (issuer)：签发人
 * exp (expiration time)：过期时间
 * sub (subject)：主题
 * aud (audience)：受众
 * nbf (Not Before)：生效时间
 * iat (Issued At)：签发时间
 * jti (JWT ID)：编号
 * JWT 默认是不加密的
 */
public interface JwtTokenService {

    /**
     * 使用HMAC算法生成token
     * @param payload
     * @return
     */
    String generateTokenByHMAC(String payload) throws JOSEException;

    /**
     * 使用HMAC算法校验token
     */
    PayloadDTO verifyTokenByHMAC(String token) throws ParseException, JOSEException;

    /**
     * 获取默认payload
     */
    PayloadDTO getDefaultPayload(UserDetails userDetails);
}
