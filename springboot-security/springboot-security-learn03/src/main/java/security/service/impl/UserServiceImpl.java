package security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import security.entity.MineUser;
import security.repository.UserRepository;
import security.service.UserService;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public MineUser save(MineUser mineUser) {
        mineUser.setCreateTime(LocalDateTime.now());
        mineUser.setUpdateTime(LocalDateTime.now());
        return userRepository.save(mineUser);
    }

    @Override
    public MineUser getById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public MineUser getByName(String name) {
        return userRepository.findByName(name);
    }
}
