package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.model.Notification;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByManagerId(int managerId);

    Notification findByManagerIdAndResourceIdAndSkillId(int managerId, int resourceId, int skillId);
}