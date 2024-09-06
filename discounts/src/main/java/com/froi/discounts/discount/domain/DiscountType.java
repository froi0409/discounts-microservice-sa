package com.froi.discounts.discount.domain;

import com.froi.discounts.common.DomainEntity;
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
}
