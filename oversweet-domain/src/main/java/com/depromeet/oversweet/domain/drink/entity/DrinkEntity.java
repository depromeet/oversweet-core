package com.depromeet.oversweet.domain.drink.entity;

import com.depromeet.oversweet.domain.common.entity.BaseTimeEntity;
import com.depromeet.oversweet.domain.drink.enums.DrinkCategory;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "drink")
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
    @JoinColumn(name = "franchise_id", nullable = false)
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
}
