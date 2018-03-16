package server;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPeerGroup is a Querydsl query type for PeerGroup
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPeerGroup extends EntityPathBase<PeerGroup> {

    private static final long serialVersionUID = -2146054862L;

    public static final QPeerGroup peerGroup = new QPeerGroup("peerGroup");

    public final NumberPath<Integer> code = createNumber("code", Integer.class);

    public final StringPath name = createString("name");

    public QPeerGroup(String variable) {
        super(PeerGroup.class, forVariable(variable));
    }

    public QPeerGroup(Path<? extends PeerGroup> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPeerGroup(PathMetadata metadata) {
        super(PeerGroup.class, metadata);
    }

}

