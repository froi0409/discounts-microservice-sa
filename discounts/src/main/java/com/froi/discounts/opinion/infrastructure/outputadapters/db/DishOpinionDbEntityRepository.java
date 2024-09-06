package com.froi.discounts.opinion.infrastructure.outputadapters.db;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishOpinionDbEntityRepository extends JpaRepository<DishOpinionDbEntity, String> {
    @Query("SELECT d.dish, AVG(d.stars) AS avgStars " +
            "FROM DishOpinionDbEntity d " +
            "GROUP BY d.dish " +
            "ORDER BY avgStars DESC")
    List<Object[]> findTop5DishesWithHighestAvgStars(Pageable pageable);
}
