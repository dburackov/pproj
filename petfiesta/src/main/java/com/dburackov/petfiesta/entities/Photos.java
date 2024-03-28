package com.dburackov.petfiesta.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "photos")
public class Photos {
    @Id
    @Column(name = "photo_id")
    private Long photoId;

    @Column(name = "pet_profile_id")
    private Long petProfileId;

    @Column(name = "file_link")
    private Long fileLink;
}
