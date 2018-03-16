package server;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResource is a Querydsl query type for Resource
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QResource extends EntityPathBase<Resource> {

    private static final long serialVersionUID = 1460495993L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QResource resource = new QResource("resource");

    public final StringPath email = createString("email");

    public final QGroup group;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath location = createString("location");

    public final QResource manager;

    public final StringPath name = createString("name");

    public final ListPath<Project, QProject> projects = this.<Project, QProject>createList("projects", Project.class, QProject.class, PathInits.DIRECT2);

    public final ListPath<Skill, QSkill> skills = this.<Skill, QSkill>createList("skills", Skill.class, QSkill.class, PathInits.DIRECT2);

    public final QResourceStatus status;

    public QResource(String variable) {
        this(Resource.class, forVariable(variable), INITS);
    }

    public QResource(Path<? extends Resource> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QResource(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QResource(PathMetadata metadata, PathInits inits) {
        this(Resource.class, metadata, inits);
    }

    public QResource(Class<? extends Resource> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.group = inits.isInitialized("group") ? new QGroup(forProperty("group")) : null;
        this.manager = inits.isInitialized("manager") ? new QResource(forProperty("manager"), inits.get("manager")) : null;
        this.status = inits.isInitialized("status") ? new QResourceStatus(forProperty("status")) : null;
    }

}

