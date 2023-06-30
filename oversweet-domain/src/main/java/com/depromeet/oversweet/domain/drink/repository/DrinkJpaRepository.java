package com.depromeet.oversweet.domain.drink.repository;

import com.depromeet.oversweet.domain.drink.entity.DrinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkJpaRepository extends JpaRepository<DrinkEntity, Long> {
}
