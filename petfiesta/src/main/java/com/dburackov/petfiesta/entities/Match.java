package com.dburackov.petfiesta.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "matches")
public class Match {
    @Id
    @Column(name = "match_id")
    private UUID matchId;

    @Column(name = "first_pet_id")
    private UUID firstPetId;

    @Column(name = "second_pet_id")
    private UUID secondPetId;
}
