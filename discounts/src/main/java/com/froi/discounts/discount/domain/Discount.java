package com.froi.discounts.discount.domain;

import com.froi.discounts.common.DomainEntity;
import com.froi.discounts.discount.domain.exceptions.DiscountException;
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
    private String customerNit;

    public void validate() throws DiscountException {
        if (startDate.isAfter(endDate)) {
            throw new DiscountException("Start date must be before end date");
        }
        if (discountType == null) {
            throw new DiscountException("Discount type must not be null");
        }
        if (!isUniqueDiscount()) {
            throw new DiscountException("Discount must have a dish or a hotel and room code or a user NIT");
        }
    }

    public boolean isUniqueDiscount() {
        return (dishId != null && hotelId == null && roomCode == null && customerNit == null)
                || (hotelId != null && roomCode != null && dishId == null && customerNit == null)
                || (customerNit != null && dishId == null && hotelId == null && roomCode == null);
    }

}
