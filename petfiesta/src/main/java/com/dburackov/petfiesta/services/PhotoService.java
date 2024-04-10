package com.dburackov.petfiesta.services;

import com.dburackov.petfiesta.constants.Constants;
import com.dburackov.petfiesta.dto.photo.PhotoDto;
import com.dburackov.petfiesta.entities.PetProfile;
import com.dburackov.petfiesta.entities.Photo;
import com.dburackov.petfiesta.exceptions.NotFoundException;
import com.dburackov.petfiesta.mappers.PhotoMapper;
import com.dburackov.petfiesta.repositories.PetProfileRepository;
import com.dburackov.petfiesta.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;
    private final PetProfileRepository petProfileRepository;
    private final PhotoMapper photoMapper;

    @Autowired
    public PhotoService(PhotoRepository photoRepository,
                        PetProfileRepository petProfileRepository,
                        PhotoMapper photoMapper)
    {
        this.photoRepository = photoRepository;
        this.petProfileRepository = petProfileRepository;
        this.photoMapper = photoMapper;
    }

    public List<PhotoDto> getPhotos() {
        return photoRepository.findAll().stream().map(photoMapper::photoToPhotoDto).toList();
    }

    public PhotoDto getPhotoById(Long id) {
        Photo photo = photoRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        return photoMapper.photoToPhotoDto(photo);
    }

    public PhotoDto createPhoto(PhotoDto photoDto, Long authenticatedUserId) {
        PetProfile petProfile = petProfileRepository.findById(photoDto.getPetProfileId()).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        if (!authenticatedUserId.equals(petProfile.getUser().getId())) {
            throw new AccessDeniedException("");
        }
        Photo photo = photoMapper.photoDtoToPhoto(photoDto);
        photo.setPetProfile(petProfile);

        return photoMapper.photoToPhotoDto(photoRepository.save(photo));
    }


    public void deletePhotoById(Long id, Long authenticatedUserId) {
        Photo photo = photoRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        if (!authenticatedUserId.equals(photo.getPetProfile().getUser().getId())) {
            throw new AccessDeniedException("");
        }
        photoRepository.deleteById(id);
    }

    public List<PhotoDto> getPhotosByPetProfileId(Long petProfileId) {
        return photoRepository.findByPetProfileId(petProfileId).stream().map(photoMapper::photoToPhotoDto).toList();
    }

}
