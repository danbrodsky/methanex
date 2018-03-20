package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.model.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {
}
