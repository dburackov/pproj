package com.dburackov.petfiesta.dto.photo;

import lombok.Data;

@Data
public class PhotoDto {
    private Long id;
    private Long petProfileId;
    private String filePath;
}
