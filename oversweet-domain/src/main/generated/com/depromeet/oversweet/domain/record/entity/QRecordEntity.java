package com.depromeet.oversweet.domain.record.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecordEntity is a Querydsl query type for RecordEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecordEntity extends EntityPathBase<RecordEntity> {

    private static final long serialVersionUID = 531107346L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecordEntity recordEntity = new QRecordEntity("recordEntity");

    public final com.depromeet.oversweet.domain.common.entity.QBaseTimeEntity _super = new com.depromeet.oversweet.domain.common.entity.QBaseTimeEntity(this);

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.depromeet.oversweet.domain.drink.entity.QDrinkEntity drink;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> intakeSugar = createNumber("intakeSugar", Integer.class);

    public final com.depromeet.oversweet.domain.member.entity.QMemberEntity member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QRecordEntity(String variable) {
        this(RecordEntity.class, forVariable(variable), INITS);
    }

    public QRecordEntity(Path<? extends RecordEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecordEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecordEntity(PathMetadata metadata, PathInits inits) {
        this(RecordEntity.class, metadata, inits);
    }

    public QRecordEntity(Class<? extends RecordEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.drink = inits.isInitialized("drink") ? new com.depromeet.oversweet.domain.drink.entity.QDrinkEntity(forProperty("drink"), inits.get("drink")) : null;
        this.member = inits.isInitialized("member") ? new com.depromeet.oversweet.domain.member.entity.QMemberEntity(forProperty("member")) : null;
    }

}

