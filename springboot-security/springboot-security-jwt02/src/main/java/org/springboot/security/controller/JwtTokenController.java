package org.springboot.security.controller;

import cn.hutool.json.JSONUtil;
import com.nimbusds.jose.JOSEException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springboot.security.common.api.Rs;
import org.springboot.security.entity.PayloadDTO;
import org.springboot.security.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;


@Slf4j
@RequestMapping("/token")
@RestController
public class JwtTokenController {

    private final String secret = "secret";

    @Autowired
    private JwtTokenService jwtTokenService;

    @ApiOperation("使用对称加密（HMAC）算法生成token")
    @GetMapping("/hmac/generate")
    public Rs<String> generateTokenByHMAC() {
        try {
            PayloadDTO payloadDTO = jwtTokenService.getDefaultPayload(null);
            String token = jwtTokenService.generateTokenByHMAC(JSONUtil.toJsonStr(payloadDTO));
            return Rs.success(token);
        } catch (Exception e) {
            log.error("", e);
            return Rs.failed();
        }
    }



    @ApiOperation("使用对称加密（HMAC）算法验证token")
    @GetMapping(value = "/hmac/verify")
    @ResponseBody
    public Rs verifyTokenByHMAC(String token) {
        try {
            PayloadDTO payloadDto  = jwtTokenService.verifyTokenByHMAC(token);
            return Rs.success(payloadDto);
        } catch (ParseException | JOSEException e) {
            log.error("", e);
        }
        return Rs.failed();
    }

}

