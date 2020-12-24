package org.springboot.security.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import org.springboot.security.entity.PayloadDTO;
import org.springboot.security.service.JwtTokenService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {
    @Override
    public String generateTokenByHMAC(String payloadDTO, String secret) throws JOSEException {
        //创建JWS头、设置前面算法和类型
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256).type(JOSEObjectType.JWT).build();
        //将负载信息封装到Payload中
        Payload payload = new Payload(payloadDTO);
        //创建JWS对象
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        //创建HMAC签名器
        MACSigner signer = new MACSigner(secret);
        //签名
        jwsObject.sign(signer);
        return jwsObject.serialize();
    }

    @Override
    public PayloadDTO getDefaultPayload() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime exp = now.plusHours(1L);
        return PayloadDTO.builder()
                .sub("token")
                .iat(now.toEpochSecond(ZoneOffset.of("+8")))
                .exp(exp.toEpochSecond(ZoneOffset.of("+8")))
                .jti(UUID.randomUUID().toString())
                .username("dongyang")
                .authorities(List.of("Admin"))
                .build();
    }
}
