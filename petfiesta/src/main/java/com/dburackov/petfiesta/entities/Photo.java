package com.dburackov.petfiesta.entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @Column(name = "photo_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long photoId;

    @Column(name = "pet_profile_id")
    private Long petProfileId;

    @Column(name = "file_link")
    private Long fileLink;
}
