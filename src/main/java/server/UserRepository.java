package server;

import org.springframework.data.repository.CrudRepository;
import server.User;

/**
 * Created by stephan on 20.03.16.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
