package com.froi.discounts.discount.infrastructure.outpuports.db;

import com.froi.discounts.discount.domain.Discount;
import com.froi.discounts.discount.domain.DiscountType;

import java.util.List;

public interface FindDiscountsOutputPort {
    DiscountType findDiscountTypeById(String id);
    Discount findRoomDiscount(String roomCode, String HotelId);
    Discount findDishDiscount(String dishCode);
    Discount findCustomerDiscount(String customerNit);
    List<Discount> findRoomDiscountsBetweenDates(Discount discount);
    List<Discount> findDishDiscountsBetweenDates(Discount discount);
    List<Discount> findCustomerDiscountsBetweenDates(Discount discount);
}
