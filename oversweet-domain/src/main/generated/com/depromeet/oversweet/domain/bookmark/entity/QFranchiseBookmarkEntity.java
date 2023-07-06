package com.depromeet.oversweet.domain.bookmark.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFranchiseBookmarkEntity is a Querydsl query type for FranchiseBookmarkEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFranchiseBookmarkEntity extends EntityPathBase<FranchiseBookmarkEntity> {

    private static final long serialVersionUID = -1121341709L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFranchiseBookmarkEntity franchiseBookmarkEntity = new QFranchiseBookmarkEntity("franchiseBookmarkEntity");

    public final com.depromeet.oversweet.domain.common.entity.QBaseTimeEntity _super = new com.depromeet.oversweet.domain.common.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.depromeet.oversweet.domain.franchise.entity.QFranchiseEntity franchise;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.depromeet.oversweet.domain.member.entity.QMemberEntity member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QFranchiseBookmarkEntity(String variable) {
        this(FranchiseBookmarkEntity.class, forVariable(variable), INITS);
    }

    public QFranchiseBookmarkEntity(Path<? extends FranchiseBookmarkEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFranchiseBookmarkEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFranchiseBookmarkEntity(PathMetadata metadata, PathInits inits) {
        this(FranchiseBookmarkEntity.class, metadata, inits);
    }

    public QFranchiseBookmarkEntity(Class<? extends FranchiseBookmarkEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.franchise = inits.isInitialized("franchise") ? new com.depromeet.oversweet.domain.franchise.entity.QFranchiseEntity(forProperty("franchise")) : null;
        this.member = inits.isInitialized("member") ? new com.depromeet.oversweet.domain.member.entity.QMemberEntity(forProperty("member")) : null;
    }

}

