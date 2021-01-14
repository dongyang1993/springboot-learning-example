package org.springboot.security.service.impl;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import org.springboot.security.entity.PayloadDTO;
import org.springboot.security.exception.JwtExpiredException;
import org.springboot.security.exception.JwtInvalidException;
import org.springboot.security.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Integer expiration;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public String generateTokenByHMAC(String payloadDTO) throws JOSEException {
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


    @Override
    public PayloadDTO verifyTokenByHMAC(String token) throws ParseException, JOSEException {
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

    @Override
    public PayloadDTO getDefaultPayload(UserDetails userDetails) {
        long now = System.currentTimeMillis();
        long exp = now + expiration;
        return PayloadDTO.builder()
                .sub("token")
                .iat(now)
                .exp(exp)
                .jti(UUID.randomUUID().toString())
                .username(userDetails.getUsername())
                .authorities(List.of("Admin"))
                .build();
    }
}
