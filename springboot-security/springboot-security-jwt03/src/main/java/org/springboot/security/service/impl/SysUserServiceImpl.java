package org.springboot.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springboot.security.dao.MineUserMapper;
import org.springboot.security.entity.SysUser;
import org.springboot.security.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private MineUserMapper mineUserMapper;


    @Override
    public List<SysUser> listAll() {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        return mineUserMapper.selectList(wrapper);
    }

    @Override
    public SysUser getByUsername(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return mineUserMapper.selectOne(wrapper);
    }
}
