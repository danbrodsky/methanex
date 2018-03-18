package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
