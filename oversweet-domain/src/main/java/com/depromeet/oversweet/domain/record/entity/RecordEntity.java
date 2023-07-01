package com.depromeet.oversweet.domain.record.entity;

import com.depromeet.oversweet.domain.common.entity.BaseTimeEntity;
import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import com.depromeet.oversweet.domain.member.entity.MemberEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_record_to_member"), nullable = false)
    private MemberEntity member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drink_id", foreignKey = @ForeignKey(name = "fk_record_to_drink"), nullable = false)
    private DrinkEntity drink;

    @Column(name = "count", nullable = false)
    private Integer count;

    @Column(name = "intake_sugar", nullable = false)
    private Integer intakeSugar;

    public int totalSugar() {
        return this.count * this.intakeSugar;
    }

    public int totalCalorie() {
        return this.count * this.drink.getCalorie();
    }
}
