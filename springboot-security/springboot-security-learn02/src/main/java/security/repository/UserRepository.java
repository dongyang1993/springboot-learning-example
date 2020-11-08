package security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import security.entity.UserPO;

@Repository
public interface UserRepository extends JpaRepository<UserPO, Long> {
}
