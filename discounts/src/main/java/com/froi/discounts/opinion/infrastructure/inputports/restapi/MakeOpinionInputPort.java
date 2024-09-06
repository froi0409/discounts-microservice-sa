package com.froi.discounts.opinion.infrastructure.inputports.restapi;

import com.froi.discounts.common.exceptions.NetworkMicroserviceException;
import com.froi.discounts.opinion.application.makeopinionusecase.DishOpinionRequest;
import com.froi.discounts.opinion.application.makeopinionusecase.RoomOpinionRequest;
import com.froi.discounts.opinion.domain.exceptions.OpinionException;

public interface MakeOpinionInputPort {
    void makeRoomOpinion(RoomOpinionRequest roomOpinionRequest) throws OpinionException, NetworkMicroserviceException;
    void makeDishOpinion(DishOpinionRequest dishOpinionRequest) throws OpinionException, NetworkMicroserviceException;
}
