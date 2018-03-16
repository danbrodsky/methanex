package server;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSkill is a Querydsl query type for Skill
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSkill extends EntityPathBase<Skill> {

    private static final long serialVersionUID = 820459462L;

    public static final QSkill skill = new QSkill("skill");

    public final ListPath<Category, QCategory> category = this.<Category, QCategory>createList("category", Category.class, QCategory.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final ListPath<Resource, QResource> resources = this.<Resource, QResource>createList("resources", Resource.class, QResource.class, PathInits.DIRECT2);

    public QSkill(String variable) {
        super(Skill.class, forVariable(variable));
    }

    public QSkill(Path<? extends Skill> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSkill(PathMetadata metadata) {
        super(Skill.class, metadata);
    }

}

