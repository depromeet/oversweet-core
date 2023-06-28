package com.depromeet.oversweet.domain.member.entity;

import com.depromeet.oversweet.domain.common.entity.BaseTimeEntity;
import com.depromeet.oversweet.domain.member.enums.Gender;
import com.depromeet.oversweet.domain.member.enums.SocialProvider;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "member")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_provider", nullable = false)
    private SocialProvider socialProvider;

    @Column(name = "social_id", nullable = false)
    private String socialId;

    @Column(name = "image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "height")
    private Integer height;

    @Column(name = "age")
    private Integer age;

    @Column(name = "daily_sugar")
    private Integer dailySugar;

    @Builder
    public MemberEntity(Long id, String nickname, String email, SocialProvider socialProvider, String socialId, String imageUrl, Gender gender, Integer weight, Integer height, Integer age, Integer dailySugar, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.socialProvider = socialProvider;
        this.socialId = socialId;
        this.imageUrl = imageUrl;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.dailySugar = dailySugar;
    }
}
