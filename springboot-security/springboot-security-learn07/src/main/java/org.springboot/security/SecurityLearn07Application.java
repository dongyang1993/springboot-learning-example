package org.springboot.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

//开启全局方法级权限验证
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@SpringBootApplication
public class SecurityLearn07Application {
    public static void main(String[] args) {
        SpringApplication.run(SecurityLearn07Application.class, args);
    }
}
