package com.dburackov.petfiesta.services;

import com.dburackov.petfiesta.entities.Passport;
import com.dburackov.petfiesta.entities.Photo;
import com.dburackov.petfiesta.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public List<Photo> getPhotos() {
        return photoRepository.findAll();
    }

    public Photo getPhotoById(Long id) {
        return photoRepository.findById(id).get();
    }

    public Photo createPhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    public Photo updatePhoto(Long id, Photo photo) {
        photo.setId(id);
        return photoRepository.save(photo);
    }

    public void deletePhotoById(Long id) {
        photoRepository.deleteById(id);
    }

    public List<Photo> getPetProfilePhotos(Long petProfileId) {
        return photoRepository.findByPetProfileId(petProfileId);
    }

}
