package com.dburackov.petfiesta.mappers;

import com.dburackov.petfiesta.dto.petProfile.PetProfileDto;
import com.dburackov.petfiesta.entities.PetProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PetProfileMapper {
    PetProfile petProfileDtoToPetProfile(PetProfileDto petProfileDto);

    PetProfileDto petProfileToPetProfileDto(PetProfile petProfile);

}
