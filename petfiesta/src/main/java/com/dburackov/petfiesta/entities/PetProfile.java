package com.dburackov.petfiesta.entities;

import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@Table(name = "pet_profiles")
public class PetProfile {

    @Id
    @Column(name = "pet_profile_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long petProfileId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "purpose")
    private String purpose;
}
