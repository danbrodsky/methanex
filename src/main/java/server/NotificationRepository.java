package server;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByManager(Resource manager);

    Notification findByManagerIdAndResourceIdAndSkillId(int manager, int resource, int skill);
}