package com.depromeet.oversweet.domain.franchise.entity;

import com.depromeet.oversweet.domain.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "franchise")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FranchiseEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "image_url")
    private String imageUrl;
}
