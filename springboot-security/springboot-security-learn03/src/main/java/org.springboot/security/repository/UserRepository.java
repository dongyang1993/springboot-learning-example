package org.springboot.security.repository;

import org.springboot.security.entity.MineUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<MineUser, Long> {

    MineUser findByName(String name);
}
