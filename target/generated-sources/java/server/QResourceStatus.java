package server;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QResourceStatus is a Querydsl query type for ResourceStatus
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QResourceStatus extends EntityPathBase<ResourceStatus> {

    private static final long serialVersionUID = 1777181643L;

    public static final QResourceStatus resourceStatus = new QResourceStatus("resourceStatus");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QResourceStatus(String variable) {
        super(ResourceStatus.class, forVariable(variable));
    }

    public QResourceStatus(Path<? extends ResourceStatus> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResourceStatus(PathMetadata metadata) {
        super(ResourceStatus.class, metadata);
    }

}

