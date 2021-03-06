package org.springboot.security.filter;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

public class JwtAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String servletPath = filterInvocation.getRequest().getServletPath();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (CollectionUtils.isEmpty(authorities)) {
            throw new AccessDeniedException(HttpStatus.FORBIDDEN.getReasonPhrase());
        }
        long authorityCount = authorities.stream().filter(n -> servletPath.equals(n.getAuthority())).count();
        if (authorityCount == 0) {
            throw new AccessDeniedException(HttpStatus.FORBIDDEN.getReasonPhrase());
        }
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
