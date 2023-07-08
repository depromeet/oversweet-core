package com.depromeet.oversweet.domain.drink.repository;

import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DrinkJpaRepository extends JpaRepository<DrinkEntity, Long> {

    List<DrinkEntity> findByFranchiseIdAndName(Long franchiseId, String drinkName);

    @Query("SELECT new com.depromeet.oversweet.domain.drink.repository.DrinkInfoWithScrapStatus(d.id, d.name, d.imageUrl, d.size, d.calorie, d.sugar, " +
            "(CASE WHEN db IS NULL THEN false ELSE true END)) " +
            "FROM DrinkEntity d " +
            "LEFT JOIN DrinkBookmarkEntity db ON d.id = db.drink.id AND db.member.id = :memberId " +
            "WHERE d.franchise.id = :franchiseId AND d.name = :drinkName ORDER BY d.size ASC")
    List<DrinkInfoWithScrapStatus> findDrinkWithBookmarkStatus(@Param("memberId") Long memberId, @Param("franchiseId") Long franchiseId, @Param("drinkName") String drinkName);

}
