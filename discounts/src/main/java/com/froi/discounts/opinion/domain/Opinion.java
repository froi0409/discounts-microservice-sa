package com.froi.discounts.opinion.domain;

import com.froi.discounts.common.DomainEntity;
import com.froi.discounts.opinion.domain.exceptions.OpinionException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@DomainEntity
public class Opinion {
    private UUID id;
    private int stars;
    private String comment;
    private String user;
    private String dish;
    private String hotel;
    private String roomCode;

    public void validate() throws OpinionException {
        if (comment != null && comment.length() > 350) {
            throw new OpinionException("Comment must be less than 350 characters");
        }
        if (stars < 0 || stars > 5) {
            throw new OpinionException("Stars must be between 0 and 5");
        }
        if (dish != null && (hotel != null || roomCode != null)) {
            throw new OpinionException("Dish opinions must not have hotel or room code, or vice versa");
        }
        if (user == null) {
            throw new OpinionException("User must not be null");
        }
    }

}
