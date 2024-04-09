package com.dburackov.petfiesta.controllers;

import com.dburackov.petfiesta.dto.petprofiledto.PetProfileDto;
import com.dburackov.petfiesta.entities.PetProfile;
import com.dburackov.petfiesta.services.PetProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @PostMapping("/users/{userId}/pet-profiles/create")
    public PetProfileDto createPetProfile(@PathVariable Long userId,
                                          @RequestBody PetProfileDto petProfileDto,
                                          Principal principal)
    {
        return petProfileService.createPetProfile(userId, petProfileDto, Long.parseLong(principal.getName()));
    }

    @PostMapping("/users/{userId}/pet-profiles/update/{id}")
    @PreAuthorize("isAuthenticated()")
    public PetProfileDto updatePetProfile(@PathVariable Long userId,
                                          @PathVariable  Long id,
                                          @RequestBody PetProfileDto petProfileDto,
                                          Principal principal)
    {
        return petProfileService.updatePetProfile(userId, id, petProfileDto, Long.parseLong(principal.getName()));
    }

    @DeleteMapping("/users/{userId}/pet-profiles/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public void deletePetProfileById(@PathVariable Long userId,
                                     @PathVariable Long id,
                                     Principal principal)
    {
        petProfileService.deletePetProfileById(userId, id, Long.parseLong(principal.getName()));
    }

    @GetMapping("/users/{userId}/pet-profiles")
    public List<PetProfileDto> getUserPetProfiles(@PathVariable Long userId, Principal principal) {
        return petProfileService.getUserPetProfiles(userId, Long.parseLong(principal.getName()));
    }
}
