package com.froi.discounts.opinion.application.makeopinionusecase;

import com.froi.discounts.opinion.domain.Opinion;
import lombok.Value;

@Value
public class DishOpinionRequest {
    String dishId;
    String user;
    String opinion;
    Integer stars;

    public Opinion toDomain() {
        return Opinion.builder()
                .dish(dishId)
                .user(user)
                .comment(opinion)
                .stars(stars)
                .build();
    }
}
