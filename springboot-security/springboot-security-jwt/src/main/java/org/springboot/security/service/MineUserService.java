package org.springboot.security.service;

import org.springboot.security.entity.MineUser;

import java.util.List;

public interface MineUserService {

    List<MineUser> listAll();

    MineUser getByUsername(String username);
}
