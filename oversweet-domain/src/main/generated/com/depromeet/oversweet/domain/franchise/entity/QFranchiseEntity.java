package com.depromeet.oversweet.domain.franchise.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFranchiseEntity is a Querydsl query type for FranchiseEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFranchiseEntity extends EntityPathBase<FranchiseEntity> {

    private static final long serialVersionUID = 866416246L;

    public static final QFranchiseEntity franchiseEntity = new QFranchiseEntity("franchiseEntity");

    public final com.depromeet.oversweet.domain.common.entity.QBaseTimeEntity _super = new com.depromeet.oversweet.domain.common.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QFranchiseEntity(String variable) {
        super(FranchiseEntity.class, forVariable(variable));
    }

    public QFranchiseEntity(Path<? extends FranchiseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFranchiseEntity(PathMetadata metadata) {
        super(FranchiseEntity.class, metadata);
    }

}

