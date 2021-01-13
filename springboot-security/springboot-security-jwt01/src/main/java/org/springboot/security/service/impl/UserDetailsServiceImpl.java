package org.springboot.security.service.impl;

import org.springboot.security.entity.MineUser;
import org.springboot.security.service.MineUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private MineUserService mineUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MineUser mineUser = mineUserService.getByUsername(username);
        if (mineUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        //权限
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_root");
        return new User(mineUser.getUsername(), mineUser.getPassword(), authorities);
    }
}
