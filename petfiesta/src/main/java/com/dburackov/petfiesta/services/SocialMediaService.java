package com.dburackov.petfiesta.services;

import com.dburackov.petfiesta.entities.SocialMedia;
import com.dburackov.petfiesta.entities.User;
import com.dburackov.petfiesta.enums.SocialMediaType;
import com.dburackov.petfiesta.repositories.SocialMediaRepository;
import com.dburackov.petfiesta.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialMediaService {
    private final SocialMediaRepository socialMediaRepository;
    private final UserRepository userRepository;

    @Autowired
    public SocialMediaService(SocialMediaRepository socialMediaRepository,
                              UserRepository userRepository)
    {
        this.socialMediaRepository = socialMediaRepository;
        this.userRepository = userRepository;
    }

    public List<SocialMedia> getSocialMedias() {
        return socialMediaRepository.findAll();
    }

    public SocialMedia getSocialMediaById(Long id) {
        return socialMediaRepository.findById(id).get();
    }

    public SocialMedia createSocialMedia(Long petProfileId, SocialMedia socialMedia, Long authenticatedUserId) {
        User user = userRepository.findByPetProfilesId(petProfileId);
        if (!authenticatedUserId.equals(user.getId())) {
            throw new AccessDeniedException("");
        }
        return socialMediaRepository.save(socialMedia);
    }

    public SocialMedia updateSocialMedia(Long petProfileId, Long id, SocialMedia socialMedia, Long authenticatedUserId) {
        User user = userRepository.findByPetProfilesId(petProfileId);
        if (!authenticatedUserId.equals(user.getId())) {
            throw new AccessDeniedException("");
        }
        socialMedia.setId(id);
        return socialMediaRepository.save(socialMedia);
    }

    public void deleteSocialMediaById(Long petProfileId, Long id, Long authenticatedUserId) {
        User user = userRepository.findByPetProfilesId(petProfileId);
        if (!authenticatedUserId.equals(user.getId())) {
            throw new AccessDeniedException("");
        }
        socialMediaRepository.deleteById(id);
    }

    public List<SocialMedia> getPetProfileSocialMedias(Long petProfileId) {
        return socialMediaRepository.findByPetProfileId(petProfileId);
    }
}
