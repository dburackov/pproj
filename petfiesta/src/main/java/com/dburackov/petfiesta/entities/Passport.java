package com.dburackov.petfiesta.entities;

import com.dburackov.petfiesta.enums.Kind;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnTransformer;


import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "passports")
public class Passport {
    @Id
    @Column(name = "passport_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_profile_id", referencedColumnName = "pet_profile_id")
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
    private Boolean breedingCertificate = false;

    @Column(name = "coat")
    private String coat = "";

    @Column(name = "bio")
    private String bio;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object.getClass() != this.getClass()) {
            return false;
        }
        Passport other = (Passport) object;
        return this.getId().equals(other.getId());
    }
}
