package server;

import java.util.List;

public interface NotificationRepositoryCustom {
    List<Skill> findNotificationsByManager(int managerId);

    Notification findNotification(int managerId, int skillId);

    void deleteNotification(int managerId, int resourceId, int skillId);
}
