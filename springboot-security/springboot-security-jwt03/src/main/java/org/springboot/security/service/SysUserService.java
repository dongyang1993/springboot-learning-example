package org.springboot.security.service;

import org.springboot.security.entity.SysUser;

import java.util.List;

public interface SysUserService {

    List<SysUser> listAll();

    SysUser getByUsername(String username);
}
