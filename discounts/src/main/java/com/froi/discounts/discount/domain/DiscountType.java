package com.froi.discounts.discount.domain;

import com.froi.discounts.common.DomainEntity;
import com.froi.discounts.discount.domain.exceptions.DiscountException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@DomainEntity
public class DiscountType {
    private UUID id;
    private String name;
    private Integer percentage;

    public void validate() throws DiscountException {
        if (name == null || name.isEmpty()) {
            throw new DiscountException("Name must not be null or empty");
        }
        if (percentage == null || percentage < 0 || percentage > 100) {
            throw new DiscountException("Percentage must be between 0 and 100");
        }
    }
}
