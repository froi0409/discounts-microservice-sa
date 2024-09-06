package com.froi.discounts.discount.infrastructure.outputadapters.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomDiscountDbEntityRepository extends JpaRepository<RoomDiscountDbEntity, String> {
    @Query(value = "SELECT * FROM room_discount WHERE room_code = :roomCode AND hotel_id = :hotelId" +
            " AND (start_date BETWEEN :startDate AND :endDate" +
            " OR end_date BETWEEN :startDate AND :endDate)", nativeQuery = true)
    List<RoomDiscountDbEntity> findDiscountsBetweenDates(@Param("roomCode") String roomCode, @Param("hotelId") String hotelId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
