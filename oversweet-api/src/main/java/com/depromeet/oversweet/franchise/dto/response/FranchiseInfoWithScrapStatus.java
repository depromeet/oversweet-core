package com.depromeet.oversweet.franchise.dto.response;

import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;

import lombok.Getter;

/**
 * 프랜차이즈 정보와 스크랩 여부를 담은 DTO
 */
@Getter
public class FranchiseInfoWithScrapStatus {

    private final Long id;
    private final String name;
    private final String imageUrl;
    private final boolean scrapStatus;

    private FranchiseInfoWithScrapStatus(Long id, String name, String imageUrl, boolean scrapStatus) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.scrapStatus = scrapStatus;
    }

    public static FranchiseInfoWithScrapStatus of(final FranchiseEntity franchise, final boolean scrapStatus) {
        return new FranchiseInfoWithScrapStatus(franchise.getId(), franchise.getName(), franchise.getImageUrl(), scrapStatus);
    }
}
