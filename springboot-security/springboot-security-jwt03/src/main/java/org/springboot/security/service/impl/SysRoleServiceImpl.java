package org.springboot.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springboot.security.dao.SysRoleMapper;
import org.springboot.security.entity.SysRole;
import org.springboot.security.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public void save(SysRole sysRole) {
        sysRoleMapper.insert(sysRole);
    }

    @Override
    public void deleteById(String id) {
        sysRoleMapper.deleteById(id);
    }

    @Override
    public SysRole getById(String id) {
        return sysRoleMapper.selectById(id);
    }

    @Override
    public List<SysRole> listAll() {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        return sysRoleMapper.selectList(wrapper);
    }
}
