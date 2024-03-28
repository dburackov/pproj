package com.dburackov.petfiesta.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "viewed_profiles")
public class ViewedProfiles {
    @Id
    @Column(name = "viewed_profile_id")
    private Long viewedProfileId;

    @Column(name = "object_pet_id")
    private Long objectPetId;

    @Column(name = "target_pet_id")
    private Long targetPetId;
}
