package com.dburackov.petfiesta.services;

import com.dburackov.petfiesta.entities.PetProfile;
import com.dburackov.petfiesta.repositories.PetProfileRepository;
import com.dburackov.petfiesta.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetProfileService {

    @Autowired
    PetProfileRepository petProfileRepository;

    public PetProfileService() {
        //because
    }

    public List<PetProfile> getPetProfiles() {
        return petProfileRepository.findAll();
    }

    public PetProfile getPetProfileById(Long id) {
        return petProfileRepository.findById(id).get();
    }

    public PetProfile addPetProfile(PetProfile petProfile) {
        return petProfileRepository.save(petProfile);
    }


    public PetProfile updatePetProfile(Long id, PetProfile petProfile) {
        petProfile.setPetProfileId(id);
        return petProfileRepository.save(petProfile);
    }

    public void deletePetProfileById(Long id) {
        petProfileRepository.deleteById(id);
    }

    public List<PetProfile> getUserPetProfiles(Long userId) {
        return petProfileRepository.findByUserId(userId);
    }
}
