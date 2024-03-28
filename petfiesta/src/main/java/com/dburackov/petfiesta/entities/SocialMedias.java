package com.dburackov.petfiesta.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "social_medias")
public class SocialMedias {
    @Id
    @Column(name = "social_media_id")
    private Long socialMediaId;

    @Column(name = "pet_profile_id")
    private Long petProfileId;

    @Column(name = "type")
    private String type;

    @Column(name = "link")
    private String link;
}
