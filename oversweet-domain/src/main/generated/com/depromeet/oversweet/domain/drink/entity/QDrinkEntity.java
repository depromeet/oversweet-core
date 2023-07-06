package com.depromeet.oversweet.domain.drink.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDrinkEntity is a Querydsl query type for DrinkEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDrinkEntity extends EntityPathBase<DrinkEntity> {

    private static final long serialVersionUID = 395583830L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDrinkEntity drinkEntity = new QDrinkEntity("drinkEntity");

    public final com.depromeet.oversweet.domain.common.entity.QBaseTimeEntity _super = new com.depromeet.oversweet.domain.common.entity.QBaseTimeEntity(this);

    public final NumberPath<Integer> calorie = createNumber("calorie", Integer.class);

    public final EnumPath<com.depromeet.oversweet.domain.drink.enums.DrinkCategory> category = createEnum("category", com.depromeet.oversweet.domain.drink.enums.DrinkCategory.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.depromeet.oversweet.domain.franchise.entity.QFranchiseEntity franchise;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final BooleanPath isMinimum = createBoolean("isMinimum");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> size = createNumber("size", Integer.class);

    public final NumberPath<Integer> sugar = createNumber("sugar", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QDrinkEntity(String variable) {
        this(DrinkEntity.class, forVariable(variable), INITS);
    }

    public QDrinkEntity(Path<? extends DrinkEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDrinkEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDrinkEntity(PathMetadata metadata, PathInits inits) {
        this(DrinkEntity.class, metadata, inits);
    }

    public QDrinkEntity(Class<? extends DrinkEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.franchise = inits.isInitialized("franchise") ? new com.depromeet.oversweet.domain.franchise.entity.QFranchiseEntity(forProperty("franchise")) : null;
    }

}

