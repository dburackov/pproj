package com.dburackov.petfiesta.controllers;

import com.dburackov.petfiesta.entities.SocialMedia;
import com.dburackov.petfiesta.services.PetProfileService;
import com.dburackov.petfiesta.services.SocialMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class SocialMediaController {

    private final SocialMediaService socialMediaService;

    @Autowired
    public SocialMediaController(SocialMediaService socialMediaService) {
        this.socialMediaService = socialMediaService;
    }

    @GetMapping("/social-medias")
    @PreAuthorize("isAuthenticated()")
    public List<SocialMedia> getSocialMedias() {
        return socialMediaService.getSocialMedias();
    }

    @GetMapping("/social-medias/{id}")
    public SocialMedia getSocialMediaById(@PathVariable Long id) {
        return socialMediaService.getSocialMediaById(id);
    }

    @PostMapping("pet-profiles/{petProfileId}/social-medias/create")
    @PreAuthorize("isAuthenticated()")
    public SocialMedia createSocialMedia(@PathVariable Long petProfileId,
                                         @RequestBody SocialMedia socialMedia,
                                         Principal principal)
    {
        return socialMediaService.createSocialMedia(petProfileId, socialMedia, Long.parseLong(principal.getName()));
    }

    @PostMapping("pet-profiles/{petProfileId}/social-medias/update/{id}")
    @PreAuthorize("isAuthenticated()")
    public SocialMedia updateSocialMedia(@PathVariable Long petProfileId,
                                         @PathVariable Long id,
                                         @RequestBody SocialMedia socialMedia,
                                         Principal principal)
    {
        return socialMediaService.updateSocialMedia(petProfileId, id, socialMedia, Long.parseLong(principal.getName()));
    }

    @DeleteMapping("pet-profiles/{petProfileId}/social-medias/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public void deleteSocialMediaById(@PathVariable Long petProfileId, @PathVariable Long id, Principal principal) {
        socialMediaService.deleteSocialMediaById(petProfileId, id, Long.parseLong(principal.getName()));
    }

    @GetMapping("/pet-profiles/{petProfileId}/social-medias")
    public List<SocialMedia> getPetProfileSocialMedias(@PathVariable Long petProfileId) {
        return socialMediaService.getPetProfileSocialMedias(petProfileId);
    }
}
