package com.dburackov.petfiesta.services;

import com.dburackov.petfiesta.constants.Constants;
import com.dburackov.petfiesta.dto.socialMedia.SocialMediaDto;
import com.dburackov.petfiesta.entities.PetProfile;
import com.dburackov.petfiesta.entities.SocialMedia;
import com.dburackov.petfiesta.entities.User;
import com.dburackov.petfiesta.exceptions.NotFoundException;
import com.dburackov.petfiesta.mappers.SocialMediaMapper;
import com.dburackov.petfiesta.repositories.PetProfileRepository;
import com.dburackov.petfiesta.repositories.SocialMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialMediaService {
    private final SocialMediaRepository socialMediaRepository;
    private final PetProfileRepository petProfileRepository;
    private final SocialMediaMapper socialMediaMapper;


    @Autowired
    public SocialMediaService(SocialMediaRepository socialMediaRepository,
                              PetProfileRepository petProfileRepository,
                              SocialMediaMapper socialMediaMapper)
    {
        this.socialMediaRepository = socialMediaRepository;
        this.petProfileRepository = petProfileRepository;
        this.socialMediaMapper = socialMediaMapper;
    }

    public List<SocialMediaDto> getSocialMedias() {
        return socialMediaRepository.findAll().stream().map(socialMediaMapper::socialMediaToSocialMediaDto).toList();
    }

    public SocialMediaDto getSocialMediaById(Long id) {
        SocialMedia socialMedia = socialMediaRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        return socialMediaMapper.socialMediaToSocialMediaDto(socialMedia);
    }

    public SocialMediaDto createSocialMedia(SocialMediaDto socialMediaDto, Long authenticatedUserId) {
        PetProfile petProfile = petProfileRepository.findById(socialMediaDto.getPetProfileId()).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        if (!authenticatedUserId.equals(petProfile.getUser().getId())) {
            throw new AccessDeniedException("");
        }
        SocialMedia socialMedia = socialMediaMapper.socialMediaDtoToSocialMedia(socialMediaDto);
        socialMedia.setPetProfile(petProfile);
        return socialMediaMapper.socialMediaToSocialMediaDto(socialMediaRepository.save(socialMedia));
    }

    public SocialMediaDto updateSocialMedia(Long id, SocialMediaDto socialMediaDto, Long authenticatedUserId) {
        PetProfile petProfile = petProfileRepository.findById(socialMediaDto.getPetProfileId()).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        if (!authenticatedUserId.equals(petProfile.getUser().getId())) {
            throw new AccessDeniedException("");
        }

        SocialMedia socialMedia = socialMediaMapper.socialMediaDtoToSocialMedia(socialMediaDto);
        socialMedia.setId(id);
        socialMedia.setPetProfile(petProfile);

        return socialMediaMapper.socialMediaToSocialMediaDto(socialMediaRepository.save(socialMedia));
    }

    public void deleteSocialMediaById(Long id, Long authenticatedUserId) {
        SocialMedia socialMedia = socialMediaRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        if (!authenticatedUserId.equals(socialMedia.getPetProfile().getUser().getId())) {
            throw new AccessDeniedException("");
        }
        socialMediaRepository.deleteById(id);
    }

    public List<SocialMediaDto> getSocialMediasByPetProfileId(Long petProfileId) {
        return socialMediaRepository.findByPetProfileId(petProfileId).stream().map(socialMediaMapper::socialMediaToSocialMediaDto).toList();
    }
}
