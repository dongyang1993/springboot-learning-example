package org.springboot.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springboot.security.dao.SysUserMapper;
import org.springboot.security.entity.SysUser;
import org.springboot.security.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;


    @Override
    public List<SysUser> listAll() {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        return sysUserMapper.selectList(wrapper);
    }

    @Override
    public SysUser getByUsername(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return sysUserMapper.selectOne(wrapper);
    }
}
