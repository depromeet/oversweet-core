package com.depromeet.oversweet.domain.franchise.repository;

import com.depromeet.oversweet.domain.franchise.entity.FranchiseEntity;
import com.depromeet.oversweet.exception.franchise.NotFoundFranchiseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindFranchiseRepositoryImplTest {

    @Mock
    private FranchiseJpaRepository franchiseJpaRepository;

    @InjectMocks
    private FindFranchiseRepositoryImpl findFranchiseRepository;

    @Test
    void 프랜차이즈_조회_성공() {
        // Given
        Long franchiseId = 1L;
        FranchiseEntity expectedFranchise = FranchiseEntity.builder()
                .id(franchiseId)
                .name("스타벅스")
                .build();

        when(franchiseJpaRepository.findById(anyLong())).thenReturn(Optional.of(expectedFranchise));

        // When
        FranchiseEntity actualFranchise = findFranchiseRepository.findFranchiseById(franchiseId);

        // Then
        assertThat(actualFranchise).isEqualTo(expectedFranchise);
    }

    @Test
    void 찾고자_하는_프랜차이즈가_없는_경우() {
        // Given
        when(franchiseJpaRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Then
        assertThatThrownBy(() -> findFranchiseRepository.findFranchiseById(1L))
                .isInstanceOf(NotFoundFranchiseException.class);
    }
}