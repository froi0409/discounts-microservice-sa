package com.froi.discounts.discount.infrastructure.outpuports.db;

import com.froi.discounts.discount.domain.Discount;
import com.froi.discounts.discount.domain.DiscountType;

public interface MakeDiscountsOutputPort {
    Discount makeRoomDiscount(Discount discount);
    Discount makeDishDiscount(Discount discount);
    Discount makeCustomerDiscount(Discount discount);

}
