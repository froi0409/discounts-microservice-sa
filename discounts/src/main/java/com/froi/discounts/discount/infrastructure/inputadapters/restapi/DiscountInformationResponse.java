package com.froi.discounts.discount.infrastructure.inputadapters.restapi;

import com.froi.discounts.discount.domain.Discount;
import lombok.Value;

@Value
public class DiscountInformationResponse {
    String discountDescription;
    Integer discountPercentage;

    public static DiscountInformationResponse fromDomain(Discount discount) {
        return new DiscountInformationResponse(discount.getDescription(), discount.getDiscountType().getPercentage());
    }
}
