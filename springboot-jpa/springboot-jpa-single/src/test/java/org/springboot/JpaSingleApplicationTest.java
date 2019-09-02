package org.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springboot.jpa.UserInfo;
import org.springboot.jpa.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JpaSingleApplicationTest {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Test
    public void test1() {
        UserInfo userInfo = new UserInfo("Herny", 25, "WH");
        UserInfo info = userInfoRepository.save(userInfo);
        System.out.println(info);
    }

    @Test
    public void test2() {
        long count = userInfoRepository.count();
        System.out.println(count);

        List<UserInfo> infoList = userInfoRepository.findAll();
    }
}
