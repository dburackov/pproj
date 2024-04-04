package com.dburackov.petfiesta.controllers;

import com.dburackov.petfiesta.entities.SocialMedia;
import com.dburackov.petfiesta.services.SocialMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SocialMediaController {

    private final SocialMediaService socialMediaService;

    @Autowired
    public SocialMediaController(SocialMediaService socialMediaService) {
        this.socialMediaService = socialMediaService;
    }

    @GetMapping("/social-medias")
    public List<SocialMedia> getSocialMedias() {
        return socialMediaService.getSocialMedias();
    }

    @GetMapping("/social-medias/{id}")
    public SocialMedia getSocialMediaById(@PathVariable Long id) {
        return socialMediaService.getSocialMediaById(id);
    }

    @PostMapping("/social-medias/create")
    public SocialMedia createSocialMedia(@RequestBody SocialMedia socialMedia) {
        return socialMediaService.createSocialMedia(socialMedia);
    }

    @PostMapping("/social-medias/update/{id}")
    public SocialMedia updateSocialMedia(@PathVariable Long id, @RequestBody SocialMedia socialMedia) {
        return socialMediaService.updateSocialMedia(id, socialMedia);
    }

    @DeleteMapping("/social-medias/delete/{id}")
    public void deleteSocialMediaById(@PathVariable Long id) {
        socialMediaService.deleteSocialMediaById(id);
    }

    @GetMapping("/pet-profiles/{petProfileId}/social-medias")
    public List<SocialMedia> getPetProfileSocialMedias(@PathVariable Long petProfileId) {
        return socialMediaService.getPetProfileSocialMedias(petProfileId);
    }
}
