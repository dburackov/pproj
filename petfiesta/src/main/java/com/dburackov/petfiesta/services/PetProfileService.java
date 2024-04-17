package com.dburackov.petfiesta.services;

import com.dburackov.petfiesta.constants.Constants;
import com.dburackov.petfiesta.dto.petProfile.PetProfileDto;
import com.dburackov.petfiesta.entities.PetProfile;
import com.dburackov.petfiesta.entities.User;
import com.dburackov.petfiesta.exceptions.NotFoundException;
import com.dburackov.petfiesta.mappers.PetProfileMapper;
import com.dburackov.petfiesta.repositories.PetProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PetProfileService {

    private final PetProfileRepository petProfileRepository;
    private final PetProfileMapper petProfileMapper;

    @Autowired
    public PetProfileService(PetProfileRepository petProfileRepository,
                             PetProfileMapper petProfileMapper)
    {
        this.petProfileRepository = petProfileRepository;
        this.petProfileMapper = petProfileMapper;
    }

    public List<PetProfileDto> getPetProfiles() {
        return petProfileRepository.findAll().stream().map(petProfileMapper::petProfileToPetProfileDto).toList();
    }

    public PetProfileDto getPetProfileById(Long id) {
        PetProfile petProfile = petProfileRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        return petProfileMapper.petProfileToPetProfileDto(petProfile);
    }

    public PetProfileDto createPetProfile(PetProfileDto petProfileDto, Long authenticatedUserId) {
        if (!authenticatedUserId.equals(petProfileDto.getUserId())) {
            throw new AccessDeniedException("");
        }

        PetProfile petProfile = petProfileMapper.petProfileDtoToPetProfile(petProfileDto);

        User user = new User();
        user.setId(petProfileDto.getUserId());
        petProfile.setUser(user);

        return petProfileMapper.petProfileToPetProfileDto(petProfileRepository.save(petProfile));
    }


    public PetProfileDto updatePetProfile(Long id, PetProfileDto petProfileDto, Long authenticatedUserId) {
        if (!authenticatedUserId.equals(petProfileDto.getUserId())) {
            throw new AccessDeniedException("");
        }
        PetProfile petProfile = petProfileRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        petProfile.setPurpose(petProfileDto.getPurpose());
        petProfile.setLikedBy(petProfileDto.getLikedBy());
        petProfile.setLikedPetProfiles(petProfileDto.getLikedPetProfiles());
        petProfile.setMatches(petProfileDto.getMatches());
        petProfile.setViewedProfiles(petProfileDto.getViewedProfiles());

        return petProfileMapper.petProfileToPetProfileDto(petProfileRepository.save(petProfile));
    }

    public void deletePetProfileById(Long id, Long authenticatedUserId) {
        PetProfile petProfile = petProfileRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        if (!authenticatedUserId.equals(petProfile.getUser().getId())) {
            throw new AccessDeniedException("");
        }
        petProfileRepository.deleteById(id);
    }

    public List<PetProfileDto> getPetProfilesByUserId(Long userId, Long authenticatedUserId) {
        if (!authenticatedUserId.equals(userId)) {
            throw new AccessDeniedException("");
        }
        return petProfileRepository.findByUserId(userId).stream().map(petProfileMapper::petProfileToPetProfileDto).toList();
    }
}
