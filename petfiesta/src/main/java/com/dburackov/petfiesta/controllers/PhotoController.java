package com.dburackov.petfiesta.controllers;

import com.dburackov.petfiesta.dto.petprofiledto.PetProfileDto;
import com.dburackov.petfiesta.entities.Passport;
import com.dburackov.petfiesta.entities.Photo;
import com.dburackov.petfiesta.services.PassportService;
import com.dburackov.petfiesta.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class PhotoControler {

    @Autowired
    PhotoService photoService;

    @GetMapping("/photos")
    public List<Photo> getPhotos() {
        return photoService.getPhotos();
    }

    @GetMapping("/photos/{id}")
    public Photo getPhotoById(@PathVariable Long id) {
        return photoService.getPhotoById(id);
    }

    @PostMapping("/photos/create")
    public Photo createPhoto(@RequestBody Photo photo) {
        return photoService.createPhoto(photo);
    }

    @PostMapping("/photos/update/{id}")
    public Photo updatePhoto(@PathVariable  Long id, @RequestBody Photo photo) {
        return photoService.updatePhoto(id, photo);
    }

    @DeleteMapping("/photos/delete/{id}")
    public void deletePhotoById(@PathVariable Long id) {
        photoService.deletePhotoById(id);
    }

    @GetMapping("/pet-profiles/{petProfileId}/photos")
    public List<Photo> getPetProfilePhotos(@PathVariable Long petProfileId) {
        return photoService.getPetProfilePhotos(petProfileId);
    }
}
