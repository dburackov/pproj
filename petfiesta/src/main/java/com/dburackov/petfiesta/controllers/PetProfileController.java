package com.dburackov.petfiesta.controllers;

import com.dburackov.petfiesta.entities.PetProfile;
import com.dburackov.petfiesta.services.PetProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetProfileController {
    @Autowired
    PetProfileService petProfileService;

    @GetMapping("/pet-profiles")
    public List<PetProfile> getPetProfiles() {
        return petProfileService.getPetProfiles();
    }

    @GetMapping("/pet-profiles/{id}")
    public PetProfile getPetProfileById(@PathVariable Long id) {
        return petProfileService.getPetProfileById(id);
    }

    @PostMapping("/pet-profiles/add")
    public PetProfile addPetProfile(@RequestBody PetProfile petProfile) {
        return petProfileService.addPetProfile(petProfile);
    }

    @PostMapping("/pet-profiles/update/{id}")
    public PetProfile updatePetProfile(@PathVariable  Long id, @RequestBody PetProfile petProfile) {
        return petProfileService.updatePetProfile(id, petProfile);
    }

    @DeleteMapping("/pet-profiles/delete/{id}")
    public void deletePetProfileById(@PathVariable Long id) {
        petProfileService.deletePetProfileById(id);
    }

    @GetMapping("/users/{userId}/pet-profiles")
    public List<PetProfile> getUserPetProfiles(@PathVariable Long userId) {
        return petProfileService.getUserPetProfiles(userId);
    }
}
