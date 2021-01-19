package org.springboot.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springboot.security.entity.PayloadDTO;
import org.springboot.security.utils.TokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 这个类里面的主要职责仍然是进行登陆认证，针对于token认证
 */
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final UserDetailsService userDetailsService;
    private final String tokenHeader;
    private final String tokenHead;
    private final String secret;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, String tokenHeader, String tokenHead, String secret) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
        this.tokenHeader = tokenHeader;
        this.tokenHead = tokenHead;
        this.secret = secret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authenticationToken = this.getAuthenticationToken(request);
        if (authenticationToken != null) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
        String headerToken = request.getHeader(this.tokenHeader);
        if (StringUtils.isEmpty(headerToken) || !headerToken.startsWith(tokenHead)) {
            return null;
        }
        try {
            String token = headerToken.substring(tokenHead.length());
            PayloadDTO payloadDTO = TokenUtil.verifyTokenByHMAC(token, secret);
            String username = payloadDTO.getUsername();
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
        } catch (Exception e) {
            log.error("token验证异常", e);
            return null;
        }
    }
}
