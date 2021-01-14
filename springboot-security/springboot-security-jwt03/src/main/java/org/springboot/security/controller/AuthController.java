package org.springboot.security.controller;

import com.nimbusds.jose.JOSEException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springboot.security.common.api.Rs;
import org.springboot.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping("/auth")
@RestController
public class AuthController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private String expiration;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private AuthService authService;

    @ApiOperation("登陆")
    @PostMapping("/login")
    public Rs login(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            String token = authService.login(username, password);
            Map<String, String> map = new HashMap<>();
            map.put("token", token);
            map.put("tokenHead", tokenHead);
            return Rs.success(map);
        } catch (JOSEException e) {
            log.error("生成token异常", e);
            return Rs.failed("生成token异常");
        }
    }
}
