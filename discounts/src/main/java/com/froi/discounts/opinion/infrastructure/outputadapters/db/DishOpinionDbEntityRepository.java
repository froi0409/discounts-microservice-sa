package com.froi.discounts.opinion.infrastructure.outputadapters.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishOpinionDbEntityRepository extends JpaRepository<DishOpinionDbEntity, String> {
}
