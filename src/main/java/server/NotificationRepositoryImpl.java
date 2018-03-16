package server;

import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class NotificationRepositoryImpl implements NotificationRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Skill> findSkillNotificationsByResource(int resourceId) {
        JPAQuery<Notification> query = new JPAQuery<Notification>(entityManager);
        return query
                .select(QSkill.skill)
                .from(QNotification.notification)
                .where(QNotification
                        .notification
                        .manager
                        .id
                        .eq(resourceId))
                .fetch();
    }

    @Override
    public Notification findNotification(int managerId, int skillId) {
        JPAQuery<Notification> query = new JPAQuery<Notification>(entityManager);
        QNotification notification = QNotification.notification;
        return query
                .from(notification)
                .where(notification
                        .notificationId
                        .managerId
                        .eq(managerId)
                        .and(notification
                                .notificationId
                                .skillId.eq(skillId)))
                .fetchOne();

    }

    @Override
    public void deleteNotification(int resourceId, int skillId) {
        Notification notification = this.findNotification(resourceId, skillId);
        entityManager.remove(new Notification(resourceId, skillId));
    }
}
