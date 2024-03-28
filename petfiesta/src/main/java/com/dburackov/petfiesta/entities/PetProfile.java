package com.dburackov.petfiesta.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;


@Data
@Entity
@Table(name = "pet_profiles")
public class PetProfile {

    @Id
    @Column(name = "pet_profile_id")
    private UUID petProfileId;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "purpose")
    private String purpose;
}
