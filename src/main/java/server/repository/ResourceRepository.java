package server.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import server.model.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Integer> {
}
