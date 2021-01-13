package org.springboot.security.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.springboot.security.dao")
public class MybatisConfig {
}
