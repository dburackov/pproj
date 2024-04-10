package com.dburackov.petfiesta.dto.petProfile;

import com.dburackov.petfiesta.enums.Purpose;
import lombok.Data;

import java.util.Set;

@Data
public class PetProfileDto {
    private Long id;
    private Long userId;
    private Purpose purpose;
    private Set<Long> likedBy;
    private Set<Long> likedPetProfiles;
    private Set<Long> matches;
    private Set<Long> viewedProfiles;
}
