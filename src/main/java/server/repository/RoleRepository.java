package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.model.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findByName(String name);
}
