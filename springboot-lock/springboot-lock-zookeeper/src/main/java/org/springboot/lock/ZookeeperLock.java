package org.springboot.lock;

public interface ZookeeperLock {

    boolean lock();

    boolean unlock();
}
