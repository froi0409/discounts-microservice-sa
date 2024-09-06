package com.froi.discounts.opinion.infrastructure.outputadapters.db;

import com.froi.discounts.common.PersistenceAdapter;
import com.froi.discounts.opinion.domain.Opinion;
import com.froi.discounts.opinion.infrastructure.outputports.db.MakeOpinionOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@PersistenceAdapter
@Transactional(rollbackFor = Exception.class)
public class OpinionDbOutputAdapter implements MakeOpinionOutputPort {

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
}
