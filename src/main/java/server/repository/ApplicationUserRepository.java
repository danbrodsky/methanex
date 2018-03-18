package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.model.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Integer> {
    ApplicationUser findByUsername(String username);
}
