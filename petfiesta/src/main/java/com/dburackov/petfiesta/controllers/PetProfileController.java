package com.dburackov.petfiesta.controllers;

import com.dburackov.petfiesta.dto.petProfile.PetProfileDto;
import com.dburackov.petfiesta.services.PetProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/pet-profiles")
public class PetProfileController {
    private final PetProfileService petProfileService;

    @Autowired
    public PetProfileController(PetProfileService petProfileService) {
        this.petProfileService = petProfileService;
    }

    @GetMapping("")
    public List<PetProfileDto> getPetProfiles() {
        return petProfileService.getPetProfiles();
    }

    @GetMapping("/{id}")
    public PetProfileDto getPetProfileById(@PathVariable Long id) {
        return petProfileService.getPetProfileById(id);
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public PetProfileDto createPetProfile(@RequestBody PetProfileDto petProfileDto,
                                          Principal principal)
    {
        return petProfileService.createPetProfile(petProfileDto, Long.parseLong(principal.getName()));
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("isAuthenticated()")
    public PetProfileDto updatePetProfile(@PathVariable Long id,
                                          @RequestBody PetProfileDto petProfileDto,
                                          Principal principal)
    {
        return petProfileService.updatePetProfile(id, petProfileDto, Long.parseLong(principal.getName()));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public void deletePetProfileById(@PathVariable Long id,
                                     Principal principal)
    {
        petProfileService.deletePetProfileById(id, Long.parseLong(principal.getName()));
    }

    @GetMapping("/user/{userId}")
    public List<PetProfileDto> getPetProfilesByUserId(@PathVariable Long userId, Principal principal) {
        return petProfileService.getPetProfilesByUserId(userId, Long.parseLong(principal.getName()));
    }
}
