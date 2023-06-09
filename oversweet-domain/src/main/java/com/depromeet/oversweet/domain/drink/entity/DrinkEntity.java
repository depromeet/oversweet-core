package com.depromeet.oversweet.domain.drink.entity;

import com.depromeet.oversweet.domain.common.entity.BaseTimeEntity;
import com.depromeet.oversweet.domain.drink.enums.DrinkCategory;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "drink",
        indexes = {
                @Index(name = "idx_franchise_id_and_name", columnList = "franchise_id,name")
        }
)
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DrinkEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id", foreignKey = @ForeignKey(name = "fk_drink_to_franchise"), nullable = false)
    private FranchiseEntity franchise;

    @Column(name = "size", nullable = false)
    private Integer size;

    @Column(name = "sugar", nullable = false)
    private Integer sugar;

    @Column(name = "calorie")
    private Integer calorie;

    @Column(name = "image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private DrinkCategory category;

    @Column(name = "is_minimum", nullable = false)
    private Boolean isMinimum;

    @Builder
    public DrinkEntity(final Long id, final String name, final FranchiseEntity franchise, final Integer size, final Integer sugar, final Integer calorie, final String imageUrl, final DrinkCategory category, final Boolean isMinimum) {
        this.id = id;
        this.name = name;
        this.franchise = franchise;
        this.size = size;
        this.sugar = sugar;
        this.calorie = calorie;
        this.imageUrl = imageUrl;
        this.category = category;
        this.isMinimum = isMinimum;
    }
}
