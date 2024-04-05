package com.dburackov.petfiesta.controllers;

import com.dburackov.petfiesta.entities.Photo;
import com.dburackov.petfiesta.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class PhotoController {

    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }
    @GetMapping("/photos")
    @PreAuthorize("isAuthenticated()")
    public List<Photo> getPhotos() {
        return photoService.getPhotos();
    }

    @GetMapping("/photos/{id}")
    public Photo getPhotoById(@PathVariable Long id) {
        return photoService.getPhotoById(id);
    }

    @PostMapping("pet-profiles/{petProfileId}/photos/create")
    @PreAuthorize("isAuthenticated()")
    public Photo createPhoto(@PathVariable Long petProfileId,
                             @RequestBody Photo photo,
                             Principal principal)
    {
        return photoService.createPhoto(petProfileId, photo, Long.parseLong(principal.getName()));
    }

    @DeleteMapping("pet-profiles/{petProfileId}/photos/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public void deletePhotoById(@PathVariable Long petProfileId, @PathVariable Long id, Principal principal) {
        photoService.deletePhotoById(petProfileId, id, Long.parseLong(principal.getName()));
    }

    @GetMapping("/pet-profiles/{petProfileId}/photos")
    public List<Photo> getPetProfilePhotos(@PathVariable Long petProfileId) {
        return photoService.getPetProfilePhotos(petProfileId);
    }
}
