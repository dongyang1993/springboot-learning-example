package org.springboot.security.service.impl;

import org.springboot.security.entity.SysUser;
import org.springboot.security.service.SysUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("Username Not Found");
        }
        //权限
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("/sys/user/listAll,/index");
        sysUser.setAuthorities(new HashSet<>(authorities));
        return sysUser;
    }
}
