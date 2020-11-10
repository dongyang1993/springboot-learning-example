package org.springboot.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity
@SpringBootApplication
public class SecurityLearn05Application {
    public static void main(String[] args) {
        SpringApplication.run(SecurityLearn05Application.class, args);
    }
}
