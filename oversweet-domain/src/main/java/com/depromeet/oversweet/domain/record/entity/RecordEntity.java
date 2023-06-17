package com.depromeet.oversweet.domain.record.entity;

import com.depromeet.oversweet.domain.common.entity.BaseTimeEntity;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "record")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecordEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drink_id", nullable = false)
    private DrinkEntity drink;

    @Column(name = "count", nullable = false)
    private Integer count;

    @Column(name = "intake_sugar", nullable = false)
    private Integer intakeSugar;
}
