package org.springboot.security.controller;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.nimbusds.jose.JOSEException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springboot.security.common.api.Rs;
import org.springboot.security.entity.PayloadDTO;
import org.springboot.security.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


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



    @ApiOperation("使用对称加密（HMAC）算法验证token")
    @RequestMapping(value = "/hmac/verify", method = RequestMethod.GET)
    @ResponseBody
    public Rs verifyTokenByHMAC(String token) {
        try {
            PayloadDTO payloadDto  = jwtTokenService.verifyTokenByHMAC(token, SecureUtil.md5("test"));
            return Rs.success(payloadDto);
        } catch (ParseException | JOSEException e) {
            log.error("", e);
        }
        return Rs.failed();
    }

}

