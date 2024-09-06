package com.froi.discounts.opinion.application.makeopinionusecase;

import com.froi.discounts.opinion.domain.Opinion;
import lombok.Value;

@Value
public class RoomOpinionRequest {
    String roomCode;
    String hotelId;
    String user;
    String opinion;
    Integer stars;

    public Opinion toDomain() {
        return Opinion.builder()
                .roomCode(roomCode)
                .hotel(hotelId)
                .user(user)
                .comment(opinion)
                .stars(stars)
                .build();
    }
}
