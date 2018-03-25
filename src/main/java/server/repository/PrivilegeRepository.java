package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.model.Privilege;

import java.util.List;

public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {
    List<Privilege> findByName(String name);
}
