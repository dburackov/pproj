package com.dburackov.petfiesta.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "likes")
public class Likes {
    @Id
    @Column(name = "like_id")
    private Long likeId;

    @Column(name = "object_pet_id")
    private Long objectPetId;

    @Column(name = "target_pet_id")
    private Long targetPetId;

    @Column(name = "date")
    private Date date;

}
