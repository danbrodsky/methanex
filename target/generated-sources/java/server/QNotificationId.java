package server;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QNotificationId is a Querydsl query type for NotificationId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QNotificationId extends BeanPath<NotificationId> {

    private static final long serialVersionUID = -1688802927L;

    public static final QNotificationId notificationId = new QNotificationId("notificationId");

    public final NumberPath<Integer> managerId = createNumber("managerId", Integer.class);

    public final NumberPath<Integer> skillId = createNumber("skillId", Integer.class);

    public QNotificationId(String variable) {
        super(NotificationId.class, forVariable(variable));
    }

    public QNotificationId(Path<? extends NotificationId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNotificationId(PathMetadata metadata) {
        super(NotificationId.class, metadata);
    }

}

