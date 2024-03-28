package com.dburackov.petfiesta.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "social_medias")
public class SocialMedia {
    @Id
    @Column(name = "social_media_id")
    private UUID socialMediaId;

    @Column(name = "pet_profile_id")
    private UUID petProfileId;

    @Column(name = "type")
    private String type;

    @Column(name = "link")
    private String link;
}
