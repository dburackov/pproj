package com.dburackov.petfiesta.services;

import com.dburackov.petfiesta.entities.SocialMedia;
import com.dburackov.petfiesta.repositories.SocialMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialMediaService {
    private final SocialMediaRepository socialMediaRepository;

    @Autowired
    public SocialMediaService(SocialMediaRepository socialMediaRepository) {
        this.socialMediaRepository = socialMediaRepository;
    }

    public List<SocialMedia> getSocialMedias() {
        return socialMediaRepository.findAll();
    }

    public SocialMedia getSocialMediaById(Long id) {
        return socialMediaRepository.findById(id).get();
    }

    public SocialMedia createSocialMedia(SocialMedia socialMedia) {
        return socialMediaRepository.save(socialMedia);
    }

    public SocialMedia updateSocialMedia(Long id, SocialMedia socialMedia) {
        socialMedia.setId(id);
        return socialMediaRepository.save(socialMedia);
    }

    public void deleteSocialMediaById(Long id) {
        socialMediaRepository.deleteById(id);
    }

    public List<SocialMedia> getPetProfileSocialMedias(Long petProfileId) {
        return socialMediaRepository.findByPetProfileId(petProfileId);
    }
}
