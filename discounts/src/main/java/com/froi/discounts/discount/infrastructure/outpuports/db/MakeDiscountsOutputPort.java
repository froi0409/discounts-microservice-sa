package com.froi.discounts.discount.infrastructure.outpuports.db;

import com.froi.discounts.discount.domain.Discount;
import com.froi.discounts.discount.domain.DiscountType;

import java.time.LocalDate;
import java.util.List;

public interface MakeDiscountsOutputPort {
    Discount makeRoomDiscount(Discount discount);
    Discount makeDishDiscount(Discount discount);
    Discount makeCustomerDiscount(Discount discount);
    List<Discount> findRoomDiscountsBetweenDates(Discount discount);
    List<Discount> findDishDiscountsBetweenDates(Discount discount);
    List<Discount> findCustomerDiscountsBetweenDates(Discount discount);

}
