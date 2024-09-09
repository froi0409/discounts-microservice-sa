package com.froi.discounts.opinion.infrastructure.outputadapters.db;

import com.froi.discounts.common.PersistenceAdapter;
import com.froi.discounts.opinion.domain.Opinion;
import com.froi.discounts.opinion.infrastructure.outputports.db.FindAvailableDiscountsByOpinionsOutputPort;
import com.froi.discounts.opinion.infrastructure.outputports.db.MakeOpinionOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@PersistenceAdapter
@Transactional(rollbackFor = Exception.class)
public class OpinionDbOutputAdapter implements MakeOpinionOutputPort, FindAvailableDiscountsByOpinionsOutputPort {

    private RoomOpinionDbEntityRepository roomOpinionDbEntityRepository;
    private DishOpinionDbEntityRepository dishOpinionDbEntityRepository;

    @Autowired
    public OpinionDbOutputAdapter(RoomOpinionDbEntityRepository roomOpinionDbEntityRepository, DishOpinionDbEntityRepository dishOpinionDbEntityRepository) {
        this.roomOpinionDbEntityRepository = roomOpinionDbEntityRepository;
        this.dishOpinionDbEntityRepository = dishOpinionDbEntityRepository;
    }

    @Override
    public Opinion makeRoomOpinion(Opinion opinion) {
        RoomOpinionDbEntity dbEntity = RoomOpinionDbEntity.fromDomain(opinion);
        return roomOpinionDbEntityRepository.save(dbEntity).toDomain();
    }

    @Override
    public Opinion makeDishOpinion(Opinion opinion) {
        DishOpinionDbEntity dbEntity = DishOpinionDbEntity.fromDomain(opinion);
        return dishOpinionDbEntityRepository.save(dbEntity).toDomain();
    }

    @Override
    public boolean findAvailableRoomDiscountByOpinions(String roomCode, String hotelId) {
        Pageable topFive = PageRequest.of(0, 5);
        List<Object[]> topRooms = roomOpinionDbEntityRepository.findTop5RoomsWithHighestAvgStars(topFive);

        for (Object[] topRoom : topRooms) {
            if (topRoom[0].equals(roomCode) && topRoom[1].equals(hotelId)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean findAvailableDishDiscountByOpinions(String dish) {
        Pageable topFive = PageRequest.of(0, 5);
        List<Object[]> topDishes = dishOpinionDbEntityRepository.findTop5DishesWithHighestAvgStars(topFive);

        for (Object[] topDish : topDishes) {
            if (topDish[0].equals(dish)) {
                return true;
            }
        }

        return false;
    }
}
