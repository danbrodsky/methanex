package server;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ResourceHistoryRepository extends CrudRepository<ResourceHistory, Integer> {

    List<ResourceHistory> findByProjectIdAndStartDurationAfterAndEndDurationBefore(int projectId, LocalDate startDuration, LocalDate endDuration);
}