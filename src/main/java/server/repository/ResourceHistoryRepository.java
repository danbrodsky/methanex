package server.repository;

import org.springframework.data.repository.CrudRepository;
import server.model.ResourceHistory;

import java.time.LocalDate;
import java.util.List;

public interface ResourceHistoryRepository extends CrudRepository<ResourceHistory, Integer> {

    List<ResourceHistory> findByProjectIdAndStartDurationAfterAndEndDurationBefore(int projectId, LocalDate startDuration, LocalDate endDuration);
}