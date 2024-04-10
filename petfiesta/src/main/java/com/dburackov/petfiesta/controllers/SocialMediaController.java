package com.dburackov.petfiesta.controllers;

import com.dburackov.petfiesta.dto.socialMedia.SocialMediaDto;
import com.dburackov.petfiesta.services.SocialMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/social-medias")
@RestController
public class SocialMediaController {

    private final SocialMediaService socialMediaService;

    @Autowired
    public SocialMediaController(SocialMediaService socialMediaService) {
        this.socialMediaService = socialMediaService;
    }

    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    public List<SocialMediaDto> getSocialMedias() {
        return socialMediaService.getSocialMedias();
    }

    @GetMapping("/{id}")
    public SocialMediaDto getSocialMediaById(@PathVariable Long id) {
        return socialMediaService.getSocialMediaById(id);
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public SocialMediaDto createSocialMedia(@RequestBody SocialMediaDto socialMediaDto,
                                         Principal principal)
    {
        return socialMediaService.createSocialMedia(socialMediaDto, Long.parseLong(principal.getName()));
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("isAuthenticated()")
    public SocialMediaDto updateSocialMedia(@PathVariable Long id,
                                         @RequestBody SocialMediaDto socialMediaDto,
                                         Principal principal)
    {
        return socialMediaService.updateSocialMedia(id, socialMediaDto, Long.parseLong(principal.getName()));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public void deleteSocialMediaById(@PathVariable Long id, Principal principal) {
        socialMediaService.deleteSocialMediaById(id, Long.parseLong(principal.getName()));
    }

    @GetMapping("/social-medias/pet-profile/{petProfileId}")
    public List<SocialMediaDto> getSocialMediasByPetProfileId(@PathVariable Long petProfileId) {
        return socialMediaService.getSocialMediasByPetProfileId(petProfileId);
    }
}
