package org.springboot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 通过数据库认证用户名密码
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()//自定义自己编写的登陆页面
                .loginPage("/login.html")//登陆页面设置
                .loginProcessingUrl("/auth/login")//登陆访问URL控制, 这个Controller不需要我们实现，SpringSecurity自己管理，只需要一个地址就行
                .defaultSuccessUrl("/index/welcome") //登陆成功之后跳转的URL
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/","/auth/login", "/public/**").permitAll()//设置不需要认证，直接访问的URL
                .antMatchers("/auth/user").hasAuthority("admin") //hasAuthority里面权限参数可以用逗号分隔多个权限，表示需要同时拥有这些权限才行 hasAuthority("admin,manager")
//                .antMatchers("/auth/user").hasAnyAuthority("admin", "manager")//hasAnyAuthority表示拥有这些权限中的任一个就可以
                .anyRequest().authenticated()//其他访问都需要权限验证
                .and()
                .csrf().disable();//关闭CSRF防护
    }
}
