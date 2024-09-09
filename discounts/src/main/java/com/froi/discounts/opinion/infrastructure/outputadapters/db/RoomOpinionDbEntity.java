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
@Table(name = "room_opinion", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomOpinionDbEntity {
    @Id
    @Column
    private String id;

    @Column(name = "room_code")
    private String roomCode;

    @Column(name = "hotel_id")
    private String hotelId;

    @Column
    private Integer stars;

    @Column
    private String comment;

    @Column(name = "user_username")
    private String user;

    public Opinion toDomain() {
        return Opinion.builder()
                .id(UUID.fromString(id))
                .roomCode(roomCode)
                .hotel(hotelId)
                .stars(stars)
                .comment(comment)
                .user(user)
                .build();
    }

    public static RoomOpinionDbEntity fromDomain(Opinion opinion) {
        return new RoomOpinionDbEntity(UUID.randomUUID().toString(),
                opinion.getRoomCode(),
                opinion.getHotel(),
                opinion.getStars(),
                opinion.getComment(),
                opinion.getUser());
    }
}
