package org.springboot.lock.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springboot.lock.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class RedisLockImpl implements RedisLock {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisLockImpl.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${redis.keepalive.time}")
    private long keepAliveTime;

    @Value("${redis.keepalive.intervaltime}")
    private long intervalTime;


    private ScheduledExecutorService scheduledExecutorService;
    private Map<String, String> keepAliveLocks = new ConcurrentHashMap<>();

    @Override
    public boolean lock(String lockKey, String lockId, long timeout, TimeUnit timeUnit, boolean keepAlive) {
        try {
            Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, lockId, timeout, timeUnit);
            Objects.requireNonNull(flag);
            /**
             * 保活
             */
            if (flag && keepAlive) {
                keepAliveLocks.put(lockKey, "");
            }
            return flag;
        } catch (Exception e) {
            return false;
        }
    }

    @PostConstruct
    private void init() {
        LOGGER.info("初始化保活线程池，并调度任务");
        /**
         * 创建定时线程
         */
        this.scheduledExecutorService = Executors.newScheduledThreadPool(1);
        /**
         * 调度保活线程
         */
        this.scheduledExecutorService.scheduleWithFixedDelay(this::keepAlive, intervalTime, intervalTime, TimeUnit.SECONDS);
        LOGGER.info("保活任务开启");
    }

    @PreDestroy
    private void destroy() {
        this.scheduledExecutorService.shutdown();
    }

    private void keepAlive() {
        if (CollectionUtils.isEmpty(this.keepAliveLocks)) {
            return;
        }
        Set<String> keys = this.keepAliveLocks.keySet();
        for (String key : keys) {
            stringRedisTemplate.expire(key, this.keepAliveTime, TimeUnit.SECONDS);
            LOGGER.info("保活: " + key);
        }
    }

    @Override
    public boolean unlock(String lockKey, String lockId) {
        Objects.requireNonNull(lockKey);
        Objects.requireNonNull(lockId);

        /**
         * 1. 检测id是否一致，防止误解锁(如:一个线程的key刚好失效，key被另外一个线程注册，这个时候解锁就可能误解锁)
         */
        String remoteLockId = stringRedisTemplate.opsForValue().get(lockKey);

        if (Objects.equals(lockId, remoteLockId)) {
            /**
             * 删掉保活
             */
            this.keepAliveLocks.remove(lockKey);
            /**
             * 删除锁
             */
            stringRedisTemplate.delete(lockKey);
            return true;
        }
        return false;
    }
}
