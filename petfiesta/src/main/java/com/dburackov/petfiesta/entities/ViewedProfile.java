package com.dburackov.petfiesta.entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "viewed_profiles")
public class ViewedProfile {
    @Id
    @Column(name = "viewed_profile_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long viewedProfileId;

    @Column(name = "object_pet_id")
    private Long objectPetId;

    @Column(name = "target_pet_id")
    private Long targetPetId;
}
