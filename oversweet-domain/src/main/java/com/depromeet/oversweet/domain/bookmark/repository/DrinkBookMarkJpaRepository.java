package com.depromeet.oversweet.domain.bookmark.repository;

import com.depromeet.oversweet.domain.bookmark.entity.DrinkBookmarkEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DrinkBookMarkJpaRepository extends JpaRepository<DrinkBookmarkEntity, Long> {

    @EntityGraph(attributePaths = {"drink.franchise"})
    List<DrinkBookmarkEntity> findByMemberId(@Param("id") Long id);

}
