package com.froi.discounts.discount.domain;

import com.froi.discounts.common.DomainEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@DomainEntity
public class Discount {
    private UUID id;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private DiscountType discountType;
    private String dishId;
    private String roomCode;
    private String hotelId;

    public void validate() {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
        if (discountType == null) {
            throw new IllegalArgumentException("Discount type must not be null");
        }

    }
}
