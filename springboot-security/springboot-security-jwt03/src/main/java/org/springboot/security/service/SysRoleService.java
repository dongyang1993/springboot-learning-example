package org.springboot.security.service;

import org.springboot.security.entity.SysRole;

import java.util.List;

public interface SysRoleService {

    void save(SysRole sysRole);

    void deleteById(String id);

    SysRole getById(String id);

    List<SysRole> listAll();
}
