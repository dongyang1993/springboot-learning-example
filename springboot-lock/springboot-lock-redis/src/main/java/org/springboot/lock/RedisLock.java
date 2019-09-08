package org.springboot.lock;

import java.util.concurrent.TimeUnit;

public interface RedisLock {

    boolean lock(String lockKey, String lockId, long timeout, TimeUnit timeUnit, boolean keepAlive);

    boolean unlock(String lockKey, String lockId);
}
