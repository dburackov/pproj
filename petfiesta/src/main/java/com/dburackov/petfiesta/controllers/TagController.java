package com.dburackov.petfiesta.controllers;

import com.dburackov.petfiesta.entities.Tag;
import com.dburackov.petfiesta.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/tags")
    public List<Tag> getTags() {
        return tagService.getTags();
    }

    @GetMapping("/tags/{id}")
    public Tag getTagById(@PathVariable Long id) {
        return tagService.getTagById(id);
    }

    @PostMapping("/tags/create")
    public Tag createTag(@RequestBody Tag tag) {
        return tagService.createTag(tag);
    }

    @PostMapping("/tags/update/{id}")
    public Tag updateTag(@PathVariable Long id, @RequestBody Tag tag) {
        return tagService.updateTag(id, tag);
    }

    @DeleteMapping("/tags/delete/{id}")
    public void deleteTagById(@PathVariable Long id) {
        tagService.deleteTagById(id);
    }

    @GetMapping("/pet-profiles/{petProfileId}/tags")
    public List<Tag> getTagsByPetProfileId(@PathVariable Long petProfileId) {
        return tagService.getTagsByPetProfileid(petProfileId);
    }

    @PostMapping("/pet-profiles/{petProfileId}/tags/add")
    @PreAuthorize("isAuthenticated()")
    public void setPetProfileTags(@PathVariable Long petProfileId, @RequestBody Tag tag, Principal principal) {
        tagService.setPetProfileTags(petProfileId, tag, Long.parseLong(principal.getName()));
    }
}
