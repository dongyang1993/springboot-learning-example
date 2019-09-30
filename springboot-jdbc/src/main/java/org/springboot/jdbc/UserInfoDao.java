package org.springboot.jdbc;

import java.util.List;

public interface UserInfoDao {

    void save1(UserInfo userInfo);

    void save2(UserInfo userInfo);

    void save3(UserInfo userInfo);

    List<UserInfo> listAll();

    UserInfo getById(int id);
}
