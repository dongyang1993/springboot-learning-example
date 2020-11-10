package org.springboot.security.service;

import org.springboot.security.entity.MineUser;

public interface UserService {

    MineUser save(MineUser mineUser);

    MineUser getById(Long id);

    MineUser getByUsername(String name);
}
