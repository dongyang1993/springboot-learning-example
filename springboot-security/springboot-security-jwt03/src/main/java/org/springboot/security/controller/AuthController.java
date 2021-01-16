package org.springboot.security.controller;

import com.nimbusds.jose.JOSEException;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springboot.security.common.api.Rs;
import org.springboot.security.service.AuthService;
import org.springboot.security.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/auth")
@RestController
public class AuthController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private long expiration;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private AuthService authService;

    @ApiOperation("获取Token")
    @PostMapping("/getToken")
    public Rs login(@RequestParam("username") String username) {
        try {
            String payload = TokenUtil.getDefaultPayload(username, expiration);
            String token = TokenUtil.generateTokenByHMAC(payload, secret);
            return Rs.success(tokenHead + token);
        } catch (JOSEException e) {
            log.error("生成token异常", e);
            return Rs.failed("生成token异常");
        }
    }

    @ApiOperation("登陆失败")
    @RequestMapping("/fail")
    public Rs fail(@RequestAttribute(value = "msg", required = false) String msg) {
        return Rs.failed(msg);
    }

    @ApiOperation("不允许访问")
    @RequestMapping("/forbidden")
    public Rs forbidden(@RequestAttribute(value = "msg", required = false) String msg) {
        return Rs.failed(msg);
    }
}
