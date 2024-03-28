package com.dburackov.petfiesta.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "matches")
public class Matches {
    @Id
    @Column(name = "match_id")
    private Long matchId;

    @Column(name = "first_pet_id")
    private Long firstPetId;

    @Column(name = "second_pet_id")
    private Long secondPetId;
}
