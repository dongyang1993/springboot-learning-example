package org.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springboot.lock.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisApplicationTest {

    @Autowired
    private RedisLock redisLock;

    @Test
    public void test1() throws InterruptedException {
        String lockKey = "lockKey";
        String lockId = UUID.randomUUID().toString();
        boolean flag1 = redisLock.lock("lockKey", lockId, 10, TimeUnit.SECONDS, true);
        status(flag1);

        boolean flag2 = redisLock.lock("lockKey", lockId, 10, TimeUnit.SECONDS, true);
        status(flag2);

        TimeUnit.SECONDS.sleep(20);

        boolean flag3 = redisLock.lock("lockKey", lockId, 10, TimeUnit.SECONDS, true);
        status(flag3);

        redisLock.unlock("lockKey", lockId);

        System.out.println("");
    }

    private void status(boolean flag) {
        if (flag) {
            System.out.println("上锁成功");
        } else {
            System.out.println("上锁失败");
        }
    }

    @Test
    public void test2() throws InterruptedException {
        String lockKey = "lockKey";
        String lockId = UUID.randomUUID().toString();

        redisLock.lock("lockKey", lockId, 60, TimeUnit.SECONDS, false);

        TimeUnit.SECONDS.sleep(10);

        redisLock.unlock("lockKey", lockId);
    }


}
