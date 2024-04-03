package com.dburackov.petfiesta.services;

import com.dburackov.petfiesta.dto.petprofiledto.PetProfileDto;
import com.dburackov.petfiesta.entities.PetProfile;
import com.dburackov.petfiesta.mappers.PetProfileMapper;
import com.dburackov.petfiesta.repositories.PetProfileRepository;
import com.dburackov.petfiesta.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetProfileService {

    private final PetProfileRepository petProfileRepository;
    private final PetProfileMapper petProfileMapper;

    @Autowired
    public PetProfileService(PetProfileRepository petProfileRepository, PetProfileMapper petProfileMapper) {
        this.petProfileRepository = petProfileRepository;
        this.petProfileMapper = petProfileMapper;
    }

    public List<PetProfileDto> getPetProfiles() {
        return petProfileRepository.findAll().stream().map(petProfileMapper::petProfileToPetProfileDto).toList();
    }

    public PetProfileDto getPetProfileById(Long id) {
        PetProfile petProfile = petProfileRepository.findById(id).get();
        return petProfileMapper.petProfileToPetProfileDto(petProfile);
    }

    public PetProfileDto addPetProfile(PetProfileDto petProfileDto) {
        PetProfile petProfile = petProfileMapper.petProfileDtoToPetProfile(petProfileDto);
        return petProfileMapper.petProfileToPetProfileDto(petProfileRepository.save(petProfile));
    }


    public PetProfileDto updatePetProfile(Long id, PetProfileDto petProfileDto) {
        PetProfile petProfile = petProfileMapper.petProfileDtoToPetProfile(petProfileDto);
        petProfile.setId(id);
        return petProfileMapper.petProfileToPetProfileDto(petProfileRepository.save(petProfile));
    }

    public void deletePetProfileById(Long id) {
        petProfileRepository.deleteById(id);
    }

//    public List<PetProfile> getUserPetProfiles(Long userId) {
//        return petProfileRepository.findByUserId(userId);
//    }
}
