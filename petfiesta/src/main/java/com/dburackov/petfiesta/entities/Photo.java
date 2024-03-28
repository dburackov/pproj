package com.dburackov.petfiesta.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @Column(name = "photo_id")
    private UUID photoId;

    @Column(name = "pet_profile_id")
    private UUID petProfileId;

    @Column(name = "file_link")
    private Long fileLink;
}
