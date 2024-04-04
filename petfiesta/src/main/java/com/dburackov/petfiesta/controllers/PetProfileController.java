package com.dburackov.petfiesta.controllers;

import com.dburackov.petfiesta.dto.petprofiledto.PetProfileDto;
import com.dburackov.petfiesta.services.PetProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetProfileController {
    private final PetProfileService petProfileService;

    @Autowired
    public PetProfileController(PetProfileService petProfileService) {
        this.petProfileService = petProfileService;
    }

    @GetMapping("/pet-profiles")
    public List<PetProfileDto> getPetProfiles() {
        return petProfileService.getPetProfiles();
    }

    @GetMapping("/pet-profiles/{id}")
    public PetProfileDto getPetProfileById(@PathVariable Long id) {
        return petProfileService.getPetProfileById(id);
    }

    @PostMapping("/pet-profiles/create")
    public PetProfileDto createPetProfile(@RequestBody PetProfileDto petProfileDto) {
        return petProfileService.createPetProfile(petProfileDto);
    }

    @PostMapping("/pet-profiles/update/{id}")
    public PetProfileDto updatePetProfile(@PathVariable  Long id, @RequestBody PetProfileDto petProfileDto) {
        return petProfileService.updatePetProfile(id, petProfileDto);
    }

    @DeleteMapping("/pet-profiles/delete/{id}")
    public void deletePetProfileById(@PathVariable Long id) {
        petProfileService.deletePetProfileById(id);
    }

    @GetMapping("/users/{userId}/pet-profiles")
    public List<PetProfileDto> getUserPetProfiles(@PathVariable Long userId) {
        return petProfileService.getUserPetProfiles(userId);
    }
}
