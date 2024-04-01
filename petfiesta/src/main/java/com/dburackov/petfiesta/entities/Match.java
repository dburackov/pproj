package com.dburackov.petfiesta.entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "matches")
public class Match {
    @Id
    @Column(name = "match_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long matchId;

    @Column(name = "first_pet_id")
    private Long firstPetId;

    @Column(name = "second_pet_id")
    private Long secondPetId;
}
