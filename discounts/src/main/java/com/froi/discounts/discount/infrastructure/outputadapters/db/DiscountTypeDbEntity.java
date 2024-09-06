package com.froi.discounts.discount.infrastructure.outputadapters.db;

import com.froi.discounts.discount.domain.DiscountType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "discount_type", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiscountTypeDbEntity {
    @Id
    @Column
    private String id;

    @Column
    private String name;

    @Column
    private Integer percentage;

    public DiscountType toDomain() {
        return DiscountType.builder()
                .id(UUID.fromString(id))
                .name(name)
                .percentage(percentage)
                .build();
    }

    public static DiscountTypeDbEntity fromDomain(DiscountType discountType) {
        return new DiscountTypeDbEntity(UUID.randomUUID().toString(),
                discountType.getName(),
                discountType.getPercentage());
    }
}
