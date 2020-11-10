package org.springboot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springboot.security.entity.MineUser;

@Repository
public interface UserRepository extends JpaRepository<MineUser, Long> {

    MineUser findByUsername(String username);
}
