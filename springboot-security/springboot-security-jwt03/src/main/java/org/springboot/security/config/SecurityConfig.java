package org.springboot.security.config;

import org.springboot.security.filter.JwtAccessDecisionManager;
import org.springboot.security.filter.JwtAuthenticationFilter;
import org.springboot.security.filter.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private long expiration;
    @Value("${jwt.tokenHead}")
    private String tokenHead;


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        AccessDeniedHandlerImpl accessDeniedHandler = new AccessDeniedHandlerImpl();
//        accessDeniedHandler.setErrorPage("/auth/forbidden");
        return accessDeniedHandler;
    }

    /**
     * 配置AuthenticationManager
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
         * provider.setUserDetailsService(userDetailsService);
         * provider.setHideUserNotFoundExceptions(false);
         * provider.setPasswordEncoder(passwordEncoder());
         * auth.authenticationProvider(provider);
         * 这种和下面用的功能基本上是一样的，不过可以对内部的配置进行一些修改
         */
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * web.ignoring().antMatchers("/static/**"); 这一行代码是为了放开对静态资源文件访问限制，
     * 要与静态资源处理器里面的配置相匹配 registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
     * 与下面的.antMatchers("/static/**").permitAll()配置作用其实是一模一样的，有一个就可以，两个都写上也不会有问题。
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/swagger-ui/**");
//        web.ignoring().antMatchers("/static/**", "/css/**", "/js/**", "/images/**", "/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * .loginPage("/login.html")
         * 这一行代码的功能是指定自定义的登陆页面HTML，
         * 如果不自定义的话，spring security回提供默认的html不过有于里面的css文件在国外，如果不开代理显示会有问题
         * http.formLogin()加了这个会由FormLoginConfigurer进行一系列的自动配置
         */
//        http.formLogin()
//                .loginPage("/login.html")
//                .loginProcessingUrl("/auth/login")
//                .successForwardUrl("/index")
//                .permitAll();

        http.authorizeRequests()
//                .antMatchers("/auth/getToken").authenticated()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs").permitAll()
                .antMatchers("/static/**", "/css/**", "/js/**", "/images/**", "/favicon.ico").permitAll()
                .anyRequest().authenticated();//.accessDecisionManager(new JwtAccessDecisionManager());

        /**
         * 配置JWT过滤器
         * addFilterBefore在指定的Filter类之前添加过滤器
         */
        http.addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userDetailsService, tokenHeader, tokenHead, secret));

        /**
         * 配置异常情况处理
         * 不设置也会有默认配置
         */
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());

//        http.logout().addLogoutHandler();

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

    }
}
