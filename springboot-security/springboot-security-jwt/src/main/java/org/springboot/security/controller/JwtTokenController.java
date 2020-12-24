package org.springboot.security.controller;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springboot.security.common.api.Rs;
import org.springboot.security.entity.PayloadDTO;
import org.springboot.security.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequestMapping("/token")
@RestController
public class JwtTokenController {

    private final String secret = "secret";

    @Autowired
    private JwtTokenService jwtTokenService;

    @ApiOperation("使用对称加密（HMAC）算法生成token")
    @PostMapping("/hmac/generate")
    public Rs<String> generateTokenByHMAC() {
        try {
            PayloadDTO payloadDTO = jwtTokenService.getDefaultPayload();
            String token = jwtTokenService.generateTokenByHMAC(JSONUtil.toJsonStr(payloadDTO), SecureUtil.md5(secret));
            return Rs.success(token);
        } catch (Exception e) {
            log.error("", e);
            return Rs.failed();
        }
    }
}

