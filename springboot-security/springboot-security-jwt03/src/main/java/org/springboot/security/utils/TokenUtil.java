package org.springboot.security.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import org.springboot.security.entity.PayloadDTO;
import org.springboot.security.exception.JwtExpiredException;
import org.springboot.security.exception.JwtInvalidException;

import java.text.ParseException;
import java.util.UUID;


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
 *
 * https://github.com/jwtk/jjwt
 */
public final class TokenUtil {

    public static String generateTokenByHMAC(String payloadDTO, String secret) throws JOSEException {
        //创建JWS头、设置前面算法和类型
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256).type(JOSEObjectType.JWT).build();
        //将负载信息封装到Payload中
        Payload payload = new Payload(payloadDTO);
        //创建JWS对象
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        //创建HMAC签名器
        MACSigner signer = new MACSigner(SecureUtil.md5(secret));
        //签名
        jwsObject.sign(signer);
        return jwsObject.serialize();
    }


    public static PayloadDTO verifyTokenByHMAC(String token, String secret) throws ParseException, JOSEException {
        //从token中解析JWS对象
        JWSObject jwsObject = JWSObject.parse(token);
        //创建HMAC验证器
        JWSVerifier jwsVerifier = new MACVerifier(SecureUtil.md5(secret));
        if (!jwsObject.verify(jwsVerifier)) {
            throw new JwtInvalidException("token签名不合法！");
        }
        String payload = jwsObject.getPayload().toString();
        PayloadDTO payloadDto = JSONUtil.toBean(payload, PayloadDTO.class);
        if (payloadDto.getExp() < System.currentTimeMillis()) {
            throw new JwtExpiredException("token已过期！");
        }
        return payloadDto;
    }

    public static String getDefaultPayload(String username, Long expiration) {
        long now = System.currentTimeMillis();
        long exp = now + expiration;
        PayloadDTO payloadDTO = PayloadDTO.builder()
                .sub("token")
                .jti(UUID.randomUUID().toString())
                .username(username)
                .iat(now)
                .exp(exp)
                .build();

        return JacksonUtil.toJson(payloadDTO);
    }
}
