package org.springboot.security.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springboot.security.entity.SysUser;

@Mapper
public interface MineUserMapper extends BaseMapper<SysUser> {
}
