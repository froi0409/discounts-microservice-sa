package com.froi.discounts.opinion.application.makeopinionusecase;

import com.froi.discounts.common.UseCase;
import com.froi.discounts.common.exceptions.NetworkMicroserviceException;
import com.froi.discounts.opinion.domain.Opinion;
import com.froi.discounts.opinion.domain.exceptions.OpinionException;
import com.froi.discounts.opinion.infrastructure.inputports.restapi.MakeOpinionInputPort;
import com.froi.discounts.opinion.infrastructure.outputadapters.db.OpinionDbOutputAdapter;
import com.froi.discounts.opinion.infrastructure.outputadapters.restapi.OpinionRestOutputAdapter;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
public class MakeOpinionUseCase implements MakeOpinionInputPort {

    private OpinionDbOutputAdapter opinionDbOutputAdapter;
    private OpinionRestOutputAdapter opinionRestOutputAdapter;

    @Autowired
    public MakeOpinionUseCase(OpinionDbOutputAdapter opinionDbOutputAdapter, OpinionRestOutputAdapter opinionRestOutputAdapter) {
        this.opinionDbOutputAdapter = opinionDbOutputAdapter;
        this.opinionRestOutputAdapter = opinionRestOutputAdapter;
    }

    @Override
    public void makeRoomOpinion(RoomOpinionRequest roomOpinionRequest) throws OpinionException, NetworkMicroserviceException {
        Opinion opinion = roomOpinionRequest.toDomain();
        opinionValidations(opinion);

        if (!opinionRestOutputAdapter.existsRoom(roomOpinionRequest.getRoomCode(), roomOpinionRequest.getHotelId())) {
            throw new OpinionException(String.format("Room %s not found in hotel %s", roomOpinionRequest.getRoomCode(), roomOpinionRequest.getHotelId()));
        }

        opinionDbOutputAdapter.makeRoomOpinion(opinion);
    }

    @Override
    public void makeDishOpinion(DishOpinionRequest dishOpinionRequest) throws OpinionException, NetworkMicroserviceException {
        Opinion opinion = dishOpinionRequest.toDomain();
        opinionValidations(opinion);

        if (!opinionRestOutputAdapter.existsDish(dishOpinionRequest.getDishId())) {
            throw new OpinionException(String.format("Dish %s not found", dishOpinionRequest.getDishId()));
        }

        opinionDbOutputAdapter.makeDishOpinion(opinion);
    }

    private void opinionValidations(Opinion opinion) throws OpinionException {
        opinion.validate();
        // find user
    }
}
