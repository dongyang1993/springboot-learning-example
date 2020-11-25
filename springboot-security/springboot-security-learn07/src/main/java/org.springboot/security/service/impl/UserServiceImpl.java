package org.springboot.security.service.impl;

import org.springboot.security.entity.MineUser;
import org.springboot.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

//    @Autowired
//    private UserRepository userRepository;


    @Override
    public MineUser save(MineUser mineUser) {
        mineUser.setCreateTime(LocalDateTime.now());
        mineUser.setUpdateTime(LocalDateTime.now());
//        return userRepository.save(mineUser);
        return mineUser;
    }

    @Override
    public MineUser getById(Long id) {
//        return userRepository.getOne(id);
        return null;
    }

    @Override
    public MineUser getByUsername(String name) {
//        return userRepository.findByUsername(name);
        return new MineUser(1L, "root", "123456", LocalDateTime.now(), LocalDateTime.now());
    }
}
