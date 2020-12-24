package org.springboot.security.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springboot.security.entity.MineUser;

@Mapper
public interface UserMapper extends BaseMapper<MineUser> {
}
