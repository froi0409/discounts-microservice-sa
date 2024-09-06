package com.froi.discounts.discount.infrastructure.outputadapters.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DishDiscountDbEntityRepository extends JpaRepository<DishDiscountDbEntity, String> {
    @Query(value = "SELECT * FROM dish_discount WHERE dish_id = :dish" +
            " AND (start_date BETWEEN :startDate AND :endDate" +
            " OR end_date BETWEEN :startDate AND :endDate)", nativeQuery = true)
    List<DishDiscountDbEntity> findDiscountsBetweenDates(@Param("dish")String dish, @Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);
}
