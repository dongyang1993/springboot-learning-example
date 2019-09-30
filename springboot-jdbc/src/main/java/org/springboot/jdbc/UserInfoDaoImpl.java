package org.springboot.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserInfoDaoImpl implements UserInfoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private SimpleJdbcInsert simpleJdbcInsert;

    @Override
    public void save1(UserInfo userInfo) {
        String sql = "insert into t_user_info (name, age, address, create_time, modify_time) VALUES (?,?,?,?,?)";
        Object[] args = {userInfo.getName(), userInfo.getAge(), userInfo.getAddress(), userInfo.getCreateTime(), userInfo.getModifyTime()};
        jdbcTemplate.update(sql, args);
    }

    @Override
    public void save2(UserInfo userInfo) {
        String sql = "insert into t_user_info (name, age, address, create_time, modify_time) VALUES (:name,:age,:address,:createTime,:modifyTime)";
        BeanPropertySqlParameterSource bean = new BeanPropertySqlParameterSource(userInfo);
        namedParameterJdbcTemplate.update(sql, bean);
    }

    @Override
    public void save3(UserInfo userInfo) {
        String sql = "insert into t_user_info (name, age, address, create_time, modify_time) VALUES (:name,:age,:address,:createTime,:modifyTime)";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("name", userInfo.getName());
        map.addValue("age", userInfo.getAge());
        map.addValue("address", userInfo.getAddress());
        map.addValue("createTime", userInfo.getCreateTime());
        map.addValue("modifyTime", userInfo.getModifyTime());
        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public List<UserInfo> listAll() {
        String sql = "select * from t_user_info";
        SqlParameterSource parameterSource = new EmptySqlParameterSource();
        return namedParameterJdbcTemplate.query(sql, new RowMapper<UserInfo>() {
            @Override
            public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserInfo userInfo = new UserInfo();
                userInfo.setName(rs.getString("name"));
                return userInfo;
            }
        });
    }

    @Override
    public UserInfo getById(int id) {
        String sql = "select * from t_user_info";
        return jdbcTemplate.queryForObject(sql, UserInfo.class);
    }
}
