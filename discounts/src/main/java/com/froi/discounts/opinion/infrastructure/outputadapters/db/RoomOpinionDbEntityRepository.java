package com.froi.discounts.opinion.infrastructure.outputadapters.db;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomOpinionDbEntityRepository extends JpaRepository<RoomOpinionDbEntity, String> {
    @Query("SELECT r.roomCode, r.hotelId, AVG(r.stars) AS avgStars " +
            "FROM RoomOpinionDbEntity r " +
            "GROUP BY r.roomCode, r.hotelId " +
            "ORDER BY avgStars DESC")
    List<Object[]> findTop5RoomsWithHighestAvgStars(Pageable pageable);
}
