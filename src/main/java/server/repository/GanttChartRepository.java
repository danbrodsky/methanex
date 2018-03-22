package server.repository;

import org.springframework.data.repository.CrudRepository;
import server.model.GanttChart;

public interface GanttChartRepository extends CrudRepository<GanttChart, Integer> {
}
