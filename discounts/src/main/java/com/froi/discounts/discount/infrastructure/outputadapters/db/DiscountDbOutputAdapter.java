package com.froi.discounts.discount.infrastructure.outputadapters.db;

import com.froi.discounts.common.PersistenceAdapter;
import com.froi.discounts.discount.domain.Discount;
import com.froi.discounts.discount.domain.DiscountType;
import com.froi.discounts.discount.infrastructure.outpuports.db.FindDiscountsOutputPort;
import com.froi.discounts.discount.infrastructure.outpuports.db.MakeDiscountsOutputPort;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@PersistenceAdapter
@Transactional(rollbackFor = Exception.class)
public class DiscountDbOutputAdapter implements MakeDiscountsOutputPort, FindDiscountsOutputPort {

    private CustomerDiscountDbEntityRepository customerDiscountDbEntityRepository;
    private DishDiscountDbEntityRepository dishDiscountDbEntityRepository;
    private RoomDiscountDbEntityRepository roomDiscountDbEntityRepository;
    private DiscountTypeDbEntityRepository discountTypeDbEntityRepository;

    @Autowired
    public DiscountDbOutputAdapter(CustomerDiscountDbEntityRepository customerDiscountDbEntityRepository, DishDiscountDbEntityRepository dishDiscountDbEntityRepository, RoomDiscountDbEntityRepository roomDiscountDbEntityRepository, DiscountTypeDbEntityRepository discountTypeDbEntityRepository) {
        this.customerDiscountDbEntityRepository = customerDiscountDbEntityRepository;
        this.dishDiscountDbEntityRepository = dishDiscountDbEntityRepository;
        this.roomDiscountDbEntityRepository = roomDiscountDbEntityRepository;
        this.discountTypeDbEntityRepository = discountTypeDbEntityRepository;
    }

    @Override
    public Discount makeRoomDiscount(Discount discount) {
        RoomDiscountDbEntity dbEntity = RoomDiscountDbEntity.fromDomain(discount);
        dbEntity = roomDiscountDbEntityRepository.save(dbEntity);
        return dbEntity.toDomain();
    }

    @Override
    public Discount makeDishDiscount(Discount discount) {
        DishDiscountDbEntity dbEntity = DishDiscountDbEntity.fromDomain(discount);
        dbEntity = dishDiscountDbEntityRepository.save(dbEntity);
        return dbEntity.toDomain();
    }

    @Override
    public Discount makeCustomerDiscount(Discount discount) {
        CustomerDiscountDbEntity dbEntity = CustomerDiscountDbEntity.fromDomain(discount);
        dbEntity = customerDiscountDbEntityRepository.save(dbEntity);
        return dbEntity.toDomain();
    }

    @Override
    public DiscountType findDiscountTypeById(String id) {
        return discountTypeDbEntityRepository.findById(id)
                .map(DiscountTypeDbEntity::toDomain)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Discount type with id %s not found", id)));
    }

    @Override
    public Discount findRoomDiscount(String roomCode, String HotelId, LocalDate date) {
        Discount discount = roomDiscountDbEntityRepository.findFirstByRoomCodeAndHotelId(roomCode, HotelId, date)
                .map(RoomDiscountDbEntity::toDomain)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Room discount with room code %s and hotel id %s not found", roomCode, HotelId)));
        DiscountType discountType = findDiscountTypeById(discount.getDiscountType().getId().toString());
        discount.setDiscountType(discountType);
        return discount;
    }

    @Override
    public Discount findDishDiscount(String dishId, LocalDate date) {
        Discount discount = dishDiscountDbEntityRepository.findFirstByDishId(dishId, date)
                .map(DishDiscountDbEntity::toDomain)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Dish discount with id %s not found", dishId)));
        DiscountType discountType = findDiscountTypeById(discount.getDiscountType().getId().toString());
        discount.setDiscountType(discountType);
        return discount;
    }

    @Override
    public Discount findCustomerDiscount(String customerNit, LocalDate date) {
        Discount discount = customerDiscountDbEntityRepository.findFirstByCustomerNit(customerNit, date)
                .map(CustomerDiscountDbEntity::toDomain)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Customer discount with NIT %s not found", customerNit)));
        DiscountType discountType = findDiscountTypeById(discount.getDiscountType().getId().toString());
        discount.setDiscountType(discountType);
        return discount;
    }

    @Override
    public List<Discount> findRoomDiscountsBetweenDates(Discount discount) {
        return roomDiscountDbEntityRepository.findDiscountsBetweenDates(discount.getRoomCode(), discount.getHotelId(), discount.getStartDate(), discount.getEndDate())
                .stream()
                .map(RoomDiscountDbEntity::toDomain)
                .toList();
    }

    @Override
    public List<Discount> findDishDiscountsBetweenDates(Discount discount) {
        return dishDiscountDbEntityRepository.findDiscountsBetweenDates(discount.getDishId(), discount.getStartDate(), discount.getEndDate())
                .stream()
                .map(DishDiscountDbEntity::toDomain)
                .toList();
    }

    @Override
    public List<Discount> findCustomerDiscountsBetweenDates(Discount discount) {
        return customerDiscountDbEntityRepository.findDiscountsBetweenDates(discount.getCustomerNit(), discount.getStartDate(), discount.getEndDate())
                .stream()
                .map(CustomerDiscountDbEntity::toDomain)
                .toList();
    }
}
