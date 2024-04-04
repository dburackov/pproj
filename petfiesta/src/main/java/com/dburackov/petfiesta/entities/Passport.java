package com.dburackov.petfiesta.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "passports")
public class Passport {
    @Id
    @Column(name = "passport_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_profile_id")
    @JsonIgnore
    private PetProfile petProfile;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "kind")
    private String kind;

    @Column(name = "breed")
    private String breed;

    @Column(name = "breeding_certificate")
    private Boolean breedingCertificate;

    @Column(name = "coat")
    private String coat;

    @Column(name = "bio")
    private String bio;
}
