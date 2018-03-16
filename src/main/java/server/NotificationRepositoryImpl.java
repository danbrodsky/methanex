package server;

import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
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
                        .resource
                        .id
                        .eq(resourceId))
                .fetch();
    }

    @Override
    public List<Notification> findNotificationsByManager(int managerId) {
        JPAQuery<Notification> query = new JPAQuery<Notification>(entityManager);
        return query
                .select(QNotification.notification)
                .from(QNotification.notification)
                .where(QNotification
                        .notification
                        .manager
                        .id
                        .eq(managerId))
                .fetch();
    }

    @Override
    public Notification findNotification(int managerId, int resourceId, int skillId) {
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
                            .resourceId
                            .eq(resourceId))
                            .and(notification
                                    .notificationId
                                    .skillId
                                    .eq(skillId)))
                .fetchOne();

    }

    @Override
    public void deleteNotification(int managerId, int resourceId, int skillId) {
        Notification notification = this.findNotification(managerId, resourceId, skillId);
        entityManager.remove(new Notification(managerId, resourceId, skillId));
    }
}
