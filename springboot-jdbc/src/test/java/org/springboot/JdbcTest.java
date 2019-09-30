package org.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springboot.jdbc.UserInfo;
import org.springboot.jdbc.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JdbcTest {

    @Autowired
    private UserInfoDao userInfoDao;

    @Test
    public void test1() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("Henry");
        userInfo.setAge(20);
        userInfo.setAddress("WH");
        userInfo.setCreateTime(LocalDateTime.now());
        userInfo.setModifyTime(LocalDateTime.now());
        userInfoDao.save1(userInfo);
    }


    @Test
    public void test2() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("Henry");
        userInfo.setAge(21);
        userInfo.setAddress("WH");
        userInfo.setCreateTime(LocalDateTime.now());
        userInfo.setModifyTime(LocalDateTime.now());
        userInfoDao.save2(userInfo);
    }

    @Test
    public void test3() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("Henry");
        userInfo.setAge(22);
        userInfo.setAddress("WH");
        userInfo.setCreateTime(LocalDateTime.now());
        userInfo.setModifyTime(LocalDateTime.now());
        userInfoDao.save3(userInfo);
    }

    @Test
    public void test4() {
        UserInfo info = userInfoDao.getById(1);
        System.out.println(info);
    }

    @Test
    public void test5() {
        List<UserInfo> userInfos = userInfoDao.listAll();
        System.out.println("info");
    }

}
