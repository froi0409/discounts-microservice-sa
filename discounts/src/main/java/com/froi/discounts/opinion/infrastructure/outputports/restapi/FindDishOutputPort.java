package com.froi.discounts.opinion.infrastructure.outputports.restapi;

import com.froi.discounts.common.exceptions.NetworkMicroserviceException;

public interface FindDishOutputPort {
    boolean existsDish(String dishId) throws NetworkMicroserviceException;
}
