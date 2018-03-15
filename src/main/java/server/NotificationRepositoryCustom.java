package server;

import java.util.List;

public interface NotificationRepositoryCustom {
    List<Skill> findSkillNotificationsByResource(int resourceId);

    Notification findNotification(int managerId, int skillId);

    void deleteNotification(int managerId, int skillId);
}
