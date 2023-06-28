package com.depromeet.oversweet.domain.bookmark.entity;

import com.depromeet.oversweet.domain.common.entity.BaseTimeEntity;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
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

import java.time.LocalDateTime;

@Table(name = "franchise_bookmark")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FranchiseBookmarkEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_franchiseBookmark_to_member"), nullable = false)
    private MemberEntity member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id", foreignKey = @ForeignKey(name = "fk_franchiseBookmark_to_franchise"), nullable = false)
    private FranchiseEntity franchise;


    public FranchiseBookmarkEntity(MemberEntity member, FranchiseEntity franchise, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.member = member;
        this.franchise = franchise;
    }
}
