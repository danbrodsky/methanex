package server;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProjectStatus is a Querydsl query type for ProjectStatus
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProjectStatus extends EntityPathBase<ProjectStatus> {

    private static final long serialVersionUID = 1686419680L;

    public static final QProjectStatus projectStatus = new QProjectStatus("projectStatus");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QProjectStatus(String variable) {
        super(ProjectStatus.class, forVariable(variable));
    }

    public QProjectStatus(Path<? extends ProjectStatus> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProjectStatus(PathMetadata metadata) {
        super(ProjectStatus.class, metadata);
    }

}

