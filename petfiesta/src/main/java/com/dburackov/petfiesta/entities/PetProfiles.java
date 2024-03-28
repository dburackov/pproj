package com.dburackov.petfiesta.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "pet_profiles")
public class PetProfiles {

    @Id
    @Column(name = "pet_profile_id")
    private Long petProfileId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "purpose")
    private String purpose;
}
