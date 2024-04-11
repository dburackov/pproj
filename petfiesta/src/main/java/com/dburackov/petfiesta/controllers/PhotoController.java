package com.dburackov.petfiesta.controllers;

import com.dburackov.petfiesta.dto.photo.PhotoDto;
import com.dburackov.petfiesta.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/photos")
public class PhotoController {

    private final PhotoService photoService;

    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    public List<PhotoDto> getPhotos() {
        return photoService.getPhotos();
    }

    @GetMapping("/{id}")
    public PhotoDto getPhotoById(@PathVariable Long id) {
        return photoService.getPhotoById(id);
    }

    @PostMapping("/photos/create")
    @PreAuthorize("isAuthenticated()")
    public PhotoDto createPhoto(@RequestBody PhotoDto photoDto,
                             Principal principal)
    {
        return photoService.createPhoto(photoDto, Long.parseLong(principal.getName()));
    }

    @DeleteMapping("/photos/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public void deletePhotoById(@PathVariable Long id, Principal principal) {
        photoService.deletePhotoById(id, Long.parseLong(principal.getName()));
    }

    @GetMapping("/photos/pet-profile/{petProfileId}")
    public List<PhotoDto> getPhotosByPetProfileId(@PathVariable Long petProfileId) {
        return photoService.getPhotosByPetProfileId(petProfileId);
    }
}
