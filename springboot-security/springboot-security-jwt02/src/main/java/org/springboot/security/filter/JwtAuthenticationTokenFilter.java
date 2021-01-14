package org.springboot.security.filter;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.extern.slf4j.Slf4j;
import org.springboot.security.entity.PayloadDTO;
import org.springboot.security.exception.JwtExpiredException;
import org.springboot.security.exception.JwtInvalidException;
import org.springboot.security.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private String expiration;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Qualifier("userDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = httpServletRequest.getHeader(tokenHeader);
        //已经有token
        if (!StringUtils.isEmpty(authHeader) && authHeader.startsWith(tokenHead)) {
            String authToken = authHeader.substring(tokenHead.length());
            try {
                PayloadDTO payloadDTO = jwtTokenService.verifyTokenByHMAC(authToken);
                String username = payloadDTO.getUsername();
                if (!StringUtils.isEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } catch (JwtInvalidException | JwtExpiredException e) {
                log.info("", e);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
