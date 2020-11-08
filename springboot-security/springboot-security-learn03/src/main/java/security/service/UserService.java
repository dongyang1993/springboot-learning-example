package security.service;

import security.entity.MineUser;

public interface UserService {

    MineUser save(MineUser mineUser);

    MineUser getById(Long id);

    MineUser getByName(String name);
}
