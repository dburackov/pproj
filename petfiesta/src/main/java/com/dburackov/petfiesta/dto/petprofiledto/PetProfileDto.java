package com.dburackov.petfiesta.dto.petprofiledto;

import com.dburackov.petfiesta.dto.userdto.UserDto;
import com.dburackov.petfiesta.entities.*;
import lombok.Data;

import java.util.List;

@Data
public class PetProfileDto {
    private Long id;
    private String purpose;
    private Long userId;
//    private User user;
//    private Passport passport;
//    private List<Photo> photos;
//    private List<Tag> tags;
//    private List<SocialMedia> socialMedias;
    private List<Long> likedBy;
    private List<Long> likedPetProfiles;
    private List<Long> matches;
    private List<Long> viewedProfiles;
}
