package com.dburackov.petfiesta.controllers;

import com.dburackov.petfiesta.dto.tag.ModifyTagDto;
import com.dburackov.petfiesta.dto.tag.TagDto;
import com.dburackov.petfiesta.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("")
    public List<TagDto> getTags() {
        return tagService.getTags();
    }

    @GetMapping("/{id}")
    public TagDto getTagById(@PathVariable Long id) {
        return tagService.getTagById(id);
    }

    @PostMapping("/create")
    public TagDto createTag(@RequestBody TagDto tagDto) {
        return tagService.createTag(tagDto);
    }

    @PostMapping("/update/{id}")
    public TagDto updateTag(@PathVariable Long id, @RequestBody TagDto tagDto) {
        return tagService.updateTag(id, tagDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTagById(@PathVariable Long id) {
        tagService.deleteTagById(id);
    }

    @GetMapping("/pet-profile/{petProfileId}")
    public List<TagDto> getTagsByPetProfileId(@PathVariable Long petProfileId) {
        return tagService.getTagsByPetProfileId(petProfileId);
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public void addPetProfileTag(@RequestBody ModifyTagDto modifyTagDto, Principal principal) {
        tagService.addPetProfileTags(modifyTagDto, Long.parseLong(principal.getName()));
    }

    @PostMapping("/delete")
    @PreAuthorize("isAuthenticated()")
    public void deletePetProfileTag(@RequestBody ModifyTagDto modifyTagDto, Principal principal) {
        tagService.deletePetProfileTag(modifyTagDto, Long.parseLong(principal.getName()));
    }
}
