package com.dburackov.petfiesta.mappers;

import com.dburackov.petfiesta.dto.socialMedia.SocialMediaDto;
import com.dburackov.petfiesta.entities.SocialMedia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SocialMediaMapper {
    SocialMedia socialMediaDtoToSocialMedia(SocialMediaDto socialMediaDto);

    SocialMediaDto socialMediaToSocialMediaDto(SocialMedia socialMedia);
}
