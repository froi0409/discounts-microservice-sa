package com.froi.discounts.discount.application.finddiscountusecase;

import com.froi.discounts.common.UseCase;
import com.froi.discounts.discount.domain.Discount;
import com.froi.discounts.discount.infrastructure.inputadapters.restapi.DiscountInformationResponse;
import com.froi.discounts.discount.infrastructure.inputports.restapi.FindDiscountInputPort;
import com.froi.discounts.discount.infrastructure.outputadapters.db.DiscountDbOutputAdapter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@UseCase
public class FindDiscountUseCase implements FindDiscountInputPort {

    private DiscountDbOutputAdapter discountDbOutputAdapter;

    @Autowired
    public FindDiscountUseCase(DiscountDbOutputAdapter discountDbOutputAdapter) {
        this.discountDbOutputAdapter = discountDbOutputAdapter;
    }

    @Override
    public DiscountInformationResponse findDishDiscount(String dishId, String date) {
        LocalDate discountDate = LocalDate.parse(date);
        Discount discount = discountDbOutputAdapter.findDishDiscount(dishId, discountDate);
        return new DiscountInformationResponse(
                discount.getDescription(),
                discount.getDiscountType().getPercentage()
        );
    }

    @Override
    public DiscountInformationResponse findRoomDiscount(String roomCode, String hotelId, String date) {
        LocalDate discountDate = LocalDate.parse(date);
        Discount discount = discountDbOutputAdapter.findRoomDiscount(roomCode, hotelId, discountDate);
        return new DiscountInformationResponse(
                discount.getDescription(),
                discount.getDiscountType().getPercentage()
        );
    }

    @Override
    public DiscountInformationResponse findCustomerDiscount(String customerNit, String date) {
        LocalDate discountDate = LocalDate.parse(date);
        Discount discount = discountDbOutputAdapter.findCustomerDiscount(customerNit, discountDate);
        return new DiscountInformationResponse(
                discount.getDescription(),
                discount.getDiscountType().getPercentage()
        );
    }
}
