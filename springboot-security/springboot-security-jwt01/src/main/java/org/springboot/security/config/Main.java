package org.springboot.security.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode1 = encoder.encode("123456");
        String encode2 = encoder.encode("123456");
        System.out.println(encode1);
        System.out.println(encode2);
    }
}
