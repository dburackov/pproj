package com.dburackov.petfiesta.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "likes")
public class Like {
    @Id
    @Column(name = "like_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long likeId;

    @Column(name = "object_pet_id")
    private Long objectPetId;

    @Column(name = "target_pet_id")
    private Long targetPetId;
}
