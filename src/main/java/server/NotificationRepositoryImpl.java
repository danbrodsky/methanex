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
    public List<Notification> findNotificationsByManager(int resourceId) {
        JPAQuery<Notification> query = new JPAQuery<Notification>(entityManager);
        return null;
    }

    @Override
    public Notification findNotification(int managerId, int skillId) {
        JPAQuery<Notification> query = new JPAQuery<Notification>(entityManager);
        QNotification notification = QNotification.notification;
        return query
                .from(notification)
                .where(notification
                        .manager
                        .id
                        .eq(managerId)
                        .and(notification
                                .skill
                                .id
                                .eq(skillId)))
                .fetchOne();

    }

    @Override
    public void deleteNotification(int managerId, int resourceId, int skillId) {
        Notification notification = this.findNotification(resourceId, skillId);
        entityManager.remove(new Notification(resourceId, skillId));
    }
}