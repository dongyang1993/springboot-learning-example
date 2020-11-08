package security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import security.entity.MineUser;
import security.service.UserService;

import java.util.List;

/**
 * 实现Spring Security提供的UserDetailsService接接口
 */
@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        MineUser mineUser = userService.getByName(userName);
        if (mineUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("role");
        return new User(mineUser.getName(), passwordEncoder.encode(mineUser.getPassword()), authorities);
    }
}
