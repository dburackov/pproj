package com.dburackov.petfiesta.entities;

import com.dburackov.petfiesta.enums.Kind;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


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
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PetProfile petProfile;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "kind")
    @ColumnTransformer(write = "?::KIND")
    private Kind kind;

    @Column(name = "breed")
    private String breed;

    @Column(name = "breeding_certificate")
    private Boolean breedingCertificate;

    @Column(name = "coat")
    private String coat;

    @Column(name = "bio")
    private String bio;
}
