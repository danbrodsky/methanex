package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.model.RagStatus;

public interface RagStatusRepository extends JpaRepository<RagStatus, Integer> {
}
