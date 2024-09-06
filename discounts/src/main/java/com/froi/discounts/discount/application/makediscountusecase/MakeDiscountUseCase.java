package com.froi.discounts.discount.application.makediscountusecase;

import com.froi.discounts.common.UseCase;
import com.froi.discounts.discount.domain.Discount;
import com.froi.discounts.discount.domain.DiscountType;
import com.froi.discounts.discount.domain.exceptions.DiscountException;
import com.froi.discounts.discount.infrastructure.inputports.restapi.MakeDiscountsInputPort;
import com.froi.discounts.discount.infrastructure.outputadapters.db.DiscountDbOutputAdapter;
import com.froi.discounts.opinion.infrastructure.outputadapters.db.OpinionDbOutputAdapter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UseCase
public class MakeDiscountUseCase implements MakeDiscountsInputPort {

    private DiscountDbOutputAdapter discountDbOutputAdapter;
    private OpinionDbOutputAdapter opinionDbOutputAdapter;

    @Autowired
    public MakeDiscountUseCase(DiscountDbOutputAdapter discountDbOutputAdapter, OpinionDbOutputAdapter opinionDbOutputAdapter) {
        this.discountDbOutputAdapter = discountDbOutputAdapter;
        this.opinionDbOutputAdapter = opinionDbOutputAdapter;
    }

    @Override
    public Discount makeDishDiscount(MakeDishDiscountRequest request) throws DiscountException {
        Discount discount = request.toDomain();
        DiscountType discountType = discountDbOutputAdapter.findDiscountTypeById(request.getDiscountType());
        discount.setDiscountType(discountType);

        validateDishDiscounts(request, discount);

        return discountDbOutputAdapter.makeDishDiscount(discount);
    }

    @Override
    public Discount makeRoomDiscount(MakeRoomDiscountRequest request) throws DiscountException {
        Discount discount = request.toDomain();
        DiscountType discountType = discountDbOutputAdapter.findDiscountTypeById(request.getDiscountType());
        discount.setDiscountType(discountType);

        validateRoomDiscounts(request, discount);

        return discountDbOutputAdapter.makeRoomDiscount(discount);
    }

    @Override
    public Discount makeCustomerDiscount(MakeCustomerDiscountRequest request) throws DiscountException {
        Discount discount = request.toDomain();
        DiscountType discountType = discountDbOutputAdapter.findDiscountTypeById(request.getDiscountType());
        discount.setDiscountType(discountType);

        validateCustomerDiscounts(request, discount);

        // Crear el descuento
        return discountDbOutputAdapter.makeCustomerDiscount(discount);
    }

    private void validateRoomDiscounts(MakeRoomDiscountRequest request, Discount discount) throws DiscountException {
        discount.validate();
        if (!opinionDbOutputAdapter.findAvailableRoomDiscountByOpinions(request.getRoomCode(), request.getHotelId())) {
            throw new DiscountException(String.format("Room %s is not available for discount. It should be in top 5 of the best rooms.", request.getRoomCode()));
        }

        List<Discount> discounts = discountDbOutputAdapter.findRoomDiscountsBetweenDates(discount);
        if (!discounts.isEmpty()) {
            throw new DiscountException(String.format("Room %s in hotel %s already has a discount between the dates %s and %s", request.getRoomCode(), request.getHotelId(), request.getStartDate(), request.getEndDate()));
        }
    }

    private void validateDishDiscounts(MakeDishDiscountRequest request, Discount discount) throws DiscountException {
        discount.validate();
        if (!opinionDbOutputAdapter.findAvailableDishDiscountByOpinions(request.getDish())) {
            throw new DiscountException(String.format("Dish %s is not available for discount. It should be in top 5 of the best dishes.", request.getDish()));
        }

        List<Discount> discounts = discountDbOutputAdapter.findDishDiscountsBetweenDates(discount);
        if (!discounts.isEmpty()) {
            throw new DiscountException(String.format("Dish %s already has a discount between the dates %s and %s", request.getDish(), request.getStartDate(), request.getEndDate()));
        }
    }

    private void validateCustomerDiscounts(MakeCustomerDiscountRequest request, Discount discount) throws DiscountException {
        discount.validate();
//        if (!opinionDbOutputAdapter.findAvailableCustomerDiscountByOpinions(request.getCustomerNit())) {
//            throw new DiscountException(String.format("Customer %s is not available for discount. It should be in top 5 of the best customers.", request.getCustomerNit()));
//        }

        List<Discount> discounts = discountDbOutputAdapter.findCustomerDiscountsBetweenDates(discount);
        if (!discounts.isEmpty()) {
            throw new DiscountException(String.format("Customer %s already has a discount between the dates %s and %s", request.getCustomerNit(), request.getStartDate(), request.getEndDate()));
        }
    }

}
