package com.dburackov.petfiesta.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "passports")
public class Passports {
    @Id
    @Column(name = "passport_id")
    private Long passportId;

    @Column(name = "pet_profile_id")
    private Long petProfileId;

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
