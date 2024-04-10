package com.dburackov.petfiesta.dto.socialMedia;

import com.dburackov.petfiesta.enums.SocialMediaType;
import lombok.Data;

@Data
public class SocialMediaDto {
    private Long id;
    private Long petProfileId;
    private SocialMediaType type;
    private String link;
}
