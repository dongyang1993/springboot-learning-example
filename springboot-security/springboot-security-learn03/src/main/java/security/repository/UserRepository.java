package security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import security.entity.MineUser;

@Repository
public interface UserRepository extends JpaRepository<MineUser, Long> {

    MineUser findByName(String name);
}
