package org.springboot.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springboot.security.dao.UserMapper;
import org.springboot.security.entity.MineUser;
import org.springboot.security.service.MineUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MineUserServiceImpl implements MineUserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public List<MineUser> listAll() {
        QueryWrapper<MineUser> wrapper = new QueryWrapper<>();
        return userMapper.selectList(wrapper);
    }
}
