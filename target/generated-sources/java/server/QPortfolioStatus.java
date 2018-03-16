package server;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPortfolioStatus is a Querydsl query type for ProjectStatus
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPortfolioStatus extends EntityPathBase<ProjectStatus> {

    private static final long serialVersionUID = -63272305L;

    public static final QPortfolioStatus portfolioStatus = new QPortfolioStatus("portfolioStatus");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QPortfolioStatus(String variable) {
        super(ProjectStatus.class, forVariable(variable));
    }

    public QPortfolioStatus(Path<? extends ProjectStatus> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPortfolioStatus(PathMetadata metadata) {
        super(ProjectStatus.class, metadata);
    }

}

