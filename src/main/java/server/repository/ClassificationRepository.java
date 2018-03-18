package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.model.Classification;

public interface ClassificationRepository extends JpaRepository<Classification, Integer> {

}
