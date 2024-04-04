package com.dburackov.petfiesta.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Data
@Entity
@Table(name = "social_medias")
public class SocialMedia {
    @Id
    @Column(name = "social_media_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "pet_profile_id")
    @JsonIgnore
    private PetProfile petProfile;

    @Column(name = "type")
    private String type;

    @Column(name = "link")
    private String link;
}
