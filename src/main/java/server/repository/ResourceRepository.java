package server.repository;
import org.springframework.data.repository.CrudRepository;
import server.model.Resource;

public interface ResourceRepository extends CrudRepository<Resource, Integer> {
}
