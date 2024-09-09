package com.froi.discounts.discount.infrastructure.outpuports.db;

import com.froi.discounts.discount.domain.Discount;
import com.froi.discounts.discount.domain.DiscountType;

import java.time.LocalDate;
import java.util.List;

public interface FindDiscountsOutputPort {
    DiscountType findDiscountTypeById(String id);
    Discount findRoomDiscount(String roomCode, String HotelId, LocalDate date);
    Discount findDishDiscount(String dishCode, LocalDate date);
    Discount findCustomerDiscount(String customerNit, LocalDate date);

}
