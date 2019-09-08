package org.springboot.lock.impl;

import org.springboot.lock.ZookeeperLock;
import org.springframework.stereotype.Service;

@Service
public class ZookeeperLockImpl implements ZookeeperLock {
    @Override
    public boolean lock() {
        return false;
    }

    @Override
    public boolean unlock() {
        return false;
    }

}
