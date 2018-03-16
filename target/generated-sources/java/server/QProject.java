package server;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProject is a Querydsl query type for Project
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProject extends EntityPathBase<Project> {

    private static final long serialVersionUID = 25898830L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProject project = new QProject("project");

    public final NumberPath<Integer> budget = createNumber("budget", Integer.class);

    public final StringPath effort = createString("effort");

    public final DateTimePath<java.util.Date> endDate = createDateTime("endDate", java.util.Date.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QResource manager;

    public final StringPath name = createString("name");

    public final QPortfolio portfolio;

    public final NumberPath<Integer> ragStatus = createNumber("ragStatus", Integer.class);

    public final ListPath<Resource, QResource> resources = this.<Resource, QResource>createList("resources", Resource.class, QResource.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> startDate = createDateTime("startDate", java.util.Date.class);

    public final QProjectStatus status;

    public QProject(String variable) {
        this(Project.class, forVariable(variable), INITS);
    }

    public QProject(Path<? extends Project> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProject(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProject(PathMetadata metadata, PathInits inits) {
        this(Project.class, metadata, inits);
    }

    public QProject(Class<? extends Project> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.manager = inits.isInitialized("manager") ? new QResource(forProperty("manager"), inits.get("manager")) : null;
        this.portfolio = inits.isInitialized("portfolio") ? new QPortfolio(forProperty("portfolio"), inits.get("portfolio")) : null;
        this.status = inits.isInitialized("status") ? new QProjectStatus(forProperty("status")) : null;
    }

}

