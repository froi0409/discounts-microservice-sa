package com.froi.discounts.discount.infrastructure.inputports.restapi;

import com.froi.discounts.discount.application.makediscountusecase.MakeCustomerDiscountRequest;
import com.froi.discounts.discount.application.makediscountusecase.MakeDishDiscountRequest;
import com.froi.discounts.discount.application.makediscountusecase.MakeRoomDiscountRequest;
import com.froi.discounts.discount.domain.Discount;
import com.froi.discounts.discount.domain.exceptions.DiscountException;

public interface MakeDiscountsInputPort {
    Discount makeDishDiscount(MakeDishDiscountRequest request) throws DiscountException;
    Discount makeRoomDiscount(MakeRoomDiscountRequest request) throws DiscountException;
    Discount makeCustomerDiscount(MakeCustomerDiscountRequest request) throws DiscountException;
}
