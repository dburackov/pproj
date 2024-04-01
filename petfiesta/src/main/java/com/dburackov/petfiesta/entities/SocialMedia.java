package com.dburackov.petfiesta.entities;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "social_medias")
public class SocialMedia {
    @Id
    @Column(name = "social_media_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pet_profile_id")
    private Long petProfileId;

    @Column(name = "type")
    private String type;

    @Column(name = "link")
    private String link;
}
