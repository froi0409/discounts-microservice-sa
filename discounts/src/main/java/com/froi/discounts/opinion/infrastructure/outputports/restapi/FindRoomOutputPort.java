package com.froi.discounts.opinion.infrastructure.outputports.restapi;

import com.froi.discounts.common.exceptions.NetworkMicroserviceException;

public interface FindRoomOutputPort {
    boolean existsRoom(String room, String hotelId) throws NetworkMicroserviceException;
}
