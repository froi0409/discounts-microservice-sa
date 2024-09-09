package com.froi.discounts.discount.infrastructure.inputports.restapi;

import com.froi.discounts.discount.infrastructure.inputadapters.restapi.DiscountInformationResponse;

public interface FindDiscountInputPort {
    DiscountInformationResponse findDishDiscount(String dishId, String date);
    DiscountInformationResponse findRoomDiscount(String roomCode, String hotelId, String date);
    DiscountInformationResponse findCustomerDiscount(String customerNit, String date);
}
