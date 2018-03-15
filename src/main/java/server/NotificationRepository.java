package server;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotificationRepository extends CrudRepository<Notification, Integer>, QueryDslPredicateExecutor<Notification>, NotificationRepositoryCustom {
    Notification findNotificationByNotificationId(NotificationId notificationId);
}