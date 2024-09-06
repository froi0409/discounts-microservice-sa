package com.froi.discounts.discount.infrastructure.outputadapters.db;

import com.froi.discounts.discount.domain.Discount;
import com.froi.discounts.discount.domain.DiscountType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "customer_discount", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDiscountDbEntity {
    @Id
    @Column
    private String id;

    @Column
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "discount_type")
    private String discountType;

    @Column(name = "customer_nit")
    private String customerNit;

    public Discount toDomain() {
        return Discount.builder()
                .id(UUID.fromString(id))
                .description(description)
                .startDate(startDate)
                .endDate(endDate)
                .discountType(DiscountType.builder().id(UUID.fromString(discountType)).build())
                .customerNit(customerNit)
                .build();
    }

    public static CustomerDiscountDbEntity fromDomain(Discount discount) {
        return new CustomerDiscountDbEntity(UUID.randomUUID().toString(),
                discount.getDescription(),
                discount.getStartDate(),
                discount.getEndDate(),
                discount.getDiscountType().getId().toString(),
                discount.getCustomerNit());
    }

}
