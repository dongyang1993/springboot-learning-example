package org.springboot.security.config;

import org.springboot.security.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * web.ignoring().antMatchers("/static/**"); 这一行代码是为了放开对静态资源文件访问限制，
     * 要与静态资源处理器里面的配置相匹配 registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
     * 与下面的.antMatchers("/static/**").permitAll()配置作用其实是一模一样的，有一个就可以，两个都写上也不会有问题。
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**", "/css/**", "/js/**", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * .loginPage("/login.html")
         * 这一行代码的功能是指定自定义的登陆页面HTML，
         * 如果不自定义的话，spring security回提供默认的html不过有于里面的css文件在国外，如果不开代理显示会有问题
         *
         */
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/auth/login")
                .successForwardUrl("/index")
                .permitAll();


        http.authorizeRequests()
                .antMatchers("/auth/**", "/token/**").permitAll()
                .anyRequest().authenticated();


        /**
         * 配置异常情况处理
         */
//        http.exceptionHandling().accessDeniedHandler();

        /**
         * 关闭CSRF防护
          */
        http.csrf().disable();

        /**
         * 因为要改成用JWT,所以调整为不创建和使用Session
         * ALWAYS       总是会新建一个Session
         * NEVER        不会新建HttpSession，但是如果有Session存在，就会使用它
         * IF_REQUIRED  如果有要求的话，会新建一个Session
         * STATELESS    不会新建，也不会使用一个HttpSession
         */
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        /**
         * 配置JWT过滤器
         * addFilterBefore在指定的Filter类之前添加过滤器
         */
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
