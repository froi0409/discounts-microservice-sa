package com.froi.discounts.opinion.infrastructure.outputports.db;

public interface FindAvailableDiscountsByOpinionsOutputPort {
    boolean findAvailableRoomDiscountByOpinions(String roomCode, String hotelId);

    boolean findAvailableDishDiscountByOpinions(String dish);
}
