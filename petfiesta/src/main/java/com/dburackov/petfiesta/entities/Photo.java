package com.dburackov.petfiesta.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @Column(name = "photo_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_link")
    private Long fileLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pet_profile_id")
    @JsonIgnore
    private PetProfile petProfile;
}
