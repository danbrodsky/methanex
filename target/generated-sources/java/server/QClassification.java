package server;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QClassification is a Querydsl query type for Classification
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QClassification extends EntityPathBase<Classification> {

    private static final long serialVersionUID = -2094720495L;

    public static final QClassification classification = new QClassification("classification");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QClassification(String variable) {
        super(Classification.class, forVariable(variable));
    }

    public QClassification(Path<? extends Classification> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClassification(PathMetadata metadata) {
        super(Classification.class, metadata);
    }

}

