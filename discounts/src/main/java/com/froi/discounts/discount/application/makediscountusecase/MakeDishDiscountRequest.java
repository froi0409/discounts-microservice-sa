package com.froi.discounts.discount.application.makediscountusecase;

import com.froi.discounts.discount.domain.Discount;
import lombok.Value;

import java.time.LocalDate;

@Value
public class MakeDishDiscountRequest {
    String dish;
    String description;
    String startDate;
    String endDate;
    String discountType;

    public Discount toDomain() {
        return Discount.builder()
                .dishId(dish)
                .description(description)
                .startDate(LocalDate.parse(startDate))
                .endDate(LocalDate.parse(endDate))
                .build();
    }
}
