package com.froi.discounts.discount.infrastructure.outputadapters.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CustomerDiscountDbEntityRepository extends JpaRepository<CustomerDiscountDbEntity, String> {
    @Query(value = "SELECT * FROM customer_discount WHERE customer_nit = :customerNit" +
            " AND (start_date BETWEEN :startDate AND :endDate" +
            " OR end_date BETWEEN :startDate AND :endDate)", nativeQuery = true)
    List<CustomerDiscountDbEntity> findDiscountsBetweenDates(@Param("customerNit") String customerNit, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
