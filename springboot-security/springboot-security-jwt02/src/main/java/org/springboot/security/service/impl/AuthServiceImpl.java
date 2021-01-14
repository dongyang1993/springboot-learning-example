package org.springboot.security.service.impl;

import com.nimbusds.jose.JOSEException;
import org.springboot.security.entity.PayloadDTO;
import org.springboot.security.service.AuthService;
import org.springboot.security.service.JwtTokenService;
import org.springboot.security.service.MineUserService;
import org.springboot.security.utils.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private MineUserService mineUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    public String login(String username, String password) throws JOSEException {
        UserDetails userDetails = mineUserService.getByUsername(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("用户名密码错误");
        }
        if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException("账号过期");
        }
        if (!userDetails.isAccountNonLocked()) {
            throw new DisabledException("账号锁定");
        }
        if (!userDetails.isEnabled()) {
            throw new LockedException("账号锁定");
        }
        if (!userDetails.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("密码过期");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        PayloadDTO payload = jwtTokenService.getDefaultPayload(userDetails);
        return jwtTokenService.generateTokenByHMAC(JacksonUtil.toJson(payload));
    }
}
