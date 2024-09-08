package com.froi.discounts.opinion.infrastructure.inputadapters.restapi;

import com.froi.discounts.common.exceptions.NetworkMicroserviceException;
import com.froi.discounts.opinion.application.makeopinionusecase.DishOpinionRequest;
import com.froi.discounts.opinion.application.makeopinionusecase.RoomOpinionRequest;
import com.froi.discounts.opinion.domain.exceptions.OpinionException;
import com.froi.discounts.opinion.infrastructure.inputports.restapi.MakeOpinionInputPort;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discounts/v1/opinions")
@SecurityRequirement(name = "bearerAuth")
public class OpinionControllerAdapter {

    private MakeOpinionInputPort makeOpinionInputPort;

    @Autowired
    public OpinionControllerAdapter(MakeOpinionInputPort makeOpinionInputPort) {
        this.makeOpinionInputPort = makeOpinionInputPort;
    }

    @PostMapping("/room")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> makeRoomOpinion(RoomOpinionRequest roomOpinionRequest) throws OpinionException, NetworkMicroserviceException {
        makeOpinionInputPort.makeRoomOpinion(roomOpinionRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/dish")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> makeDishOpinion(DishOpinionRequest dishOpinionRequest) throws OpinionException, NetworkMicroserviceException {
        makeOpinionInputPort.makeDishOpinion(dishOpinionRequest);
        return ResponseEntity.ok().build();
    }

}
