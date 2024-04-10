package com.dburackov.petfiesta.mappers;

import com.dburackov.petfiesta.dto.photo.PhotoDto;
import com.dburackov.petfiesta.entities.Photo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhotoMapper {
    Photo photoDtoToPhoto(PhotoDto photoDto);

    PhotoDto photoToPhotoDto(Photo photo);
}
