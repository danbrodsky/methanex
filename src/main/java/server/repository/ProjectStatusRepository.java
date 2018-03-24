package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.model.ProjectStatus;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Integer> {
}
