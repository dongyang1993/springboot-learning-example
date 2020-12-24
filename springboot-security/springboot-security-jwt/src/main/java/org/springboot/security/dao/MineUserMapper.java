package org.springboot.security.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springboot.security.entity.MineUser;

@Mapper
public interface MineUserMapper extends BaseMapper<MineUser> {
}
