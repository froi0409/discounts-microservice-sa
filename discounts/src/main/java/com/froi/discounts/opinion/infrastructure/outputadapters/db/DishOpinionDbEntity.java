package com.froi.discounts.opinion.infrastructure.outputadapters.db;

import com.froi.discounts.opinion.domain.Opinion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "dish_opinion", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishOpinionDbEntity {
    @Id
    @Column
    private String id;

    @Column
    private String dish;

    @Column
    private Integer stars;

    @Column
    private String comment;

    @Column(name = "user_username")
    private String user;

    public Opinion toDomain() {
        return Opinion.builder()
                .id(UUID.fromString(id))
                .dish(dish)
                .stars(stars)
                .comment(comment)
                .user(user)
                .build();
    }

    public static DishOpinionDbEntity fromDomain(Opinion opinion) {
        return new DishOpinionDbEntity(UUID.randomUUID().toString(),
                opinion.getDish(),
                opinion.getStars(),
                opinion.getComment(),
                opinion.getUser());
    }

}
