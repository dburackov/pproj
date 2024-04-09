package com.dburackov.petfiesta.dto.petprofiledto;

import com.dburackov.petfiesta.dto.userdto.UserDto;
import com.dburackov.petfiesta.entities.*;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
public class PetProfileDto {
    private Long id;
    private String purpose;
    private Set<Long> likedBy;
    private Set<Long> likedPetProfiles;
    private Set<Long> matches;
    private Set<Long> viewedProfiles;
}
