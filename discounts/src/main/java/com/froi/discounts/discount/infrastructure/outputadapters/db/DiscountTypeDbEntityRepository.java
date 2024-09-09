package com.froi.discounts.discount.infrastructure.outputadapters.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountTypeDbEntityRepository extends JpaRepository<DiscountTypeDbEntity, String> {
}
