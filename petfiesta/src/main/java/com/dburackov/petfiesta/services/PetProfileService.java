package com.dburackov.petfiesta.services;

import com.dburackov.petfiesta.dto.petprofiledto.PetProfileDto;
import com.dburackov.petfiesta.entities.PetProfile;
import com.dburackov.petfiesta.entities.User;
import com.dburackov.petfiesta.mappers.PetProfileMapper;
import com.dburackov.petfiesta.repositories.PetProfileRepository;
import com.dburackov.petfiesta.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class PetProfileService {

    private final PetProfileRepository petProfileRepository;
    private final UserRepository userRepository;
    private final PetProfileMapper petProfileMapper;

    @Autowired
    public PetProfileService(PetProfileRepository petProfileRepository,
                             PetProfileMapper petProfileMapper,
                             UserRepository userRepository)
    {
        this.petProfileRepository = petProfileRepository;
        this.petProfileMapper = petProfileMapper;
        this.userRepository = userRepository;
    }

    public List<PetProfileDto> getPetProfiles() {
        return petProfileRepository.findAll().stream().map(petProfileMapper::petProfileToPetProfileDto).toList();
    }

    public PetProfileDto getPetProfileById(Long id) {
        PetProfile petProfile = petProfileRepository.findById(id).get();
        return petProfileMapper.petProfileToPetProfileDto(petProfile);
    }

    public PetProfileDto createPetProfile(Long userId, PetProfileDto petProfileDto, Long authenticatedUserId) {
        if (!authenticatedUserId.equals(userId)) {
            throw new AccessDeniedException("");
        }
        var userOpt = userRepository.findById(userId);
        User user = null;
        if (userOpt.isPresent()) {
          user = userOpt.get();
        }
        PetProfile petProfile = petProfileMapper.petProfileDtoToPetProfile(petProfileDto);
        petProfile.setUser(user);
        return petProfileMapper.petProfileToPetProfileDto(petProfileRepository.save(petProfile));
    }


    public PetProfileDto updatePetProfile(Long userId, Long id, PetProfileDto petProfileDto, Long authenticatedUserId) {
        PetProfile petProfile = petProfileMapper.petProfileDtoToPetProfile(petProfileDto);
        if (!authenticatedUserId.equals(userId)) {
            throw new AccessDeniedException("");
        }
        petProfile.setId(id);
        return petProfileMapper.petProfileToPetProfileDto(petProfileRepository.save(petProfile));
    }

    public void deletePetProfileById(Long userId, Long id, Long authenticatedUserId) {
        if (!authenticatedUserId.equals(userId)) {
            throw new AccessDeniedException("");
        }
        petProfileRepository.deleteById(id);
    }

    public List<PetProfileDto> getUserPetProfiles(Long userId, Long authenticatedUserId) {
        if (!authenticatedUserId.equals(userId)) {
            throw new AccessDeniedException("");
        }
        return petProfileRepository.findByUserId(userId).stream().map(petProfileMapper::petProfileToPetProfileDto).toList();
    }
}
