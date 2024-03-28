package com.dburackov.petfiesta.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "likes")
public class Like {
    @Id
    @Column(name = "like_id")
    private UUID likeId;

    @Column(name = "object_pet_id")
    private UUID objectPetId;

    @Column(name = "target_pet_id")
    private UUID targetPetId;

    @Column(name = "date")
    private Date date;

}
