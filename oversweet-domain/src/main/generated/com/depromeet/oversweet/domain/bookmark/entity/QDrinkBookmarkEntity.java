package com.depromeet.oversweet.domain.bookmark.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDrinkBookmarkEntity is a Querydsl query type for DrinkBookmarkEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDrinkBookmarkEntity extends EntityPathBase<DrinkBookmarkEntity> {

    private static final long serialVersionUID = 1376708398L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDrinkBookmarkEntity drinkBookmarkEntity = new QDrinkBookmarkEntity("drinkBookmarkEntity");

    public final com.depromeet.oversweet.domain.common.entity.QBaseTimeEntity _super = new com.depromeet.oversweet.domain.common.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.depromeet.oversweet.domain.drink.entity.QDrinkEntity drink;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.depromeet.oversweet.domain.member.entity.QMemberEntity member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QDrinkBookmarkEntity(String variable) {
        this(DrinkBookmarkEntity.class, forVariable(variable), INITS);
    }

    public QDrinkBookmarkEntity(Path<? extends DrinkBookmarkEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDrinkBookmarkEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDrinkBookmarkEntity(PathMetadata metadata, PathInits inits) {
        this(DrinkBookmarkEntity.class, metadata, inits);
    }

    public QDrinkBookmarkEntity(Class<? extends DrinkBookmarkEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.drink = inits.isInitialized("drink") ? new com.depromeet.oversweet.domain.drink.entity.QDrinkEntity(forProperty("drink"), inits.get("drink")) : null;
        this.member = inits.isInitialized("member") ? new com.depromeet.oversweet.domain.member.entity.QMemberEntity(forProperty("member")) : null;
    }

}

