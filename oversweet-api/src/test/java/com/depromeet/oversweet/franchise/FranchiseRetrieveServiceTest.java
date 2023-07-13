package com.depromeet.oversweet.franchise;


import com.depromeet.oversweet.common.dto.response.FranchiseInfo;
import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.domain.franchise.repository.FindFranchiseSearchRepository;
import com.depromeet.oversweet.franchise.service.FranchiseRetrieveService;
import com.depromeet.oversweet.search.service.FranchiseSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("프랜차이즈 검색 테스트")
@MockitoSettings
public class FranchiseRetrieveServiceTest {
    @Mock
    private FindFranchiseSearchRepository findFranchiseSearchRepository;

    @InjectMocks
    FranchiseSearchService franchiseSearchService;

    private FranchiseEntity firstFranchiseEntity;
    private FranchiseEntity secondFranchiseEntity;

    @BeforeEach
    void setUp() {
        firstFranchiseEntity = getFranchiseEntity(1L, "스타벅스");
        secondFranchiseEntity = getFranchiseEntity(2L, "파스쿠찌");
    }

    @Test
    @DisplayName("해당 키워드가 포함되는 프랜차이즈 목록을 조회한다.")
    void getFranchisesByKeywordTest() {
        // given
        List<FranchiseEntity> franchiseEntities = Arrays.asList(firstFranchiseEntity, secondFranchiseEntity);
        final String keyword = "스";

        Mockito.when(findFranchiseSearchRepository.findFranchiseByKeyword(keyword))
                .thenReturn(franchiseEntities);

        // when
        List<FranchiseInfo> response = franchiseSearchService.getFranchiseByKeyword(keyword);

        // then
        assertThat(response.size() == 2);
        assertThat(response.get(0).name().equals(firstFranchiseEntity.getName()));
        assertThat(response.get(0).id().equals(firstFranchiseEntity.getId()));
        assertThat(response.get(0).imageUrl().equals(firstFranchiseEntity.getImageUrl()));
        assertThat(response.get(1).name().equals(secondFranchiseEntity.getName()));
        assertThat(response.get(1).id().equals(secondFranchiseEntity.getId()));
        assertThat(response.get(1).imageUrl().equals(secondFranchiseEntity.getImageUrl()));
    }

    private FranchiseEntity getFranchiseEntity(final Long id, final String name) {
        final FranchiseEntity franchiseEntity = FranchiseEntity.builder()
                .id(id)
                .name(name)
                .imageUrl("test")
                .build();
        return franchiseEntity;
    }
}
