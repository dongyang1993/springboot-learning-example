package org.springboot.security.service;

import org.springboot.security.entity.SysUser;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface SysUserService {

//    @PreAuthorize("hasAuthority('/xxx')")
    List<SysUser> listAll();

    SysUser getByUsername(String username);
}
