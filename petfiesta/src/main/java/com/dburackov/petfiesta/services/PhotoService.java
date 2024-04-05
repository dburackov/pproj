package com.dburackov.petfiesta.services;

import com.dburackov.petfiesta.entities.Passport;
import com.dburackov.petfiesta.entities.Photo;
import com.dburackov.petfiesta.entities.User;
import com.dburackov.petfiesta.repositories.PhotoRepository;
import com.dburackov.petfiesta.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;

    @Autowired
    public PhotoService(PhotoRepository photoRepository,
                        UserRepository userRepository)
    {
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
    }

    public List<Photo> getPhotos() {
        return photoRepository.findAll();
    }

    public Photo getPhotoById(Long id) {
        return photoRepository.findById(id).get();
    }

    public Photo createPhoto(Long petProfileId, Photo photo, Long authenticatedUserId) {
        User user = userRepository.findByPetProfilesId(petProfileId);
        if (!authenticatedUserId.equals(user.getId())) {
            throw new AccessDeniedException("");
        }
        return photoRepository.save(photo);
    }


    public void deletePhotoById(Long petProfileId, Long id, Long authenticatedUserId) {
        User user = userRepository.findByPetProfilesId(petProfileId);
        if (!authenticatedUserId.equals(user.getId())) {
            throw new AccessDeniedException("");
        }
        photoRepository.deleteById(id);
    }

    public List<Photo> getPetProfilePhotos(Long petProfileId) {
        return photoRepository.findByPetProfileId(petProfileId);
    }

}
