package server;

import java.util.List;

public interface NotificationRepositoryCustom {
    List<Skill> findSkillNotificationsByResource(int resourceId);
    List<Skill> findSkillNotificationsByManager(int managerId);

    Notification findNotification(int managerId, int resourceId, int skillId);

    void deleteNotification(int managerId, int resourceId, int skillId);
}
