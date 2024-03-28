package com.dburackov.petfiesta.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "viewed_profiles")
public class ViewedProfile {
    @Id
    @Column(name = "viewed_profile_id")
    private UUID viewedProfileId;

    @Column(name = "object_pet_id")
    private UUID objectPetId;

    @Column(name = "target_pet_id")
    private UUID targetPetId;
}
