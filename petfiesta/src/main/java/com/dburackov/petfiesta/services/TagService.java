package com.dburackov.petfiesta.services;

import com.dburackov.petfiesta.entities.PetProfile;
import com.dburackov.petfiesta.entities.Tag;
import com.dburackov.petfiesta.repositories.PetProfileRepository;
import com.dburackov.petfiesta.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private final TagRepository tagRepository;
    private final PetProfileRepository petProfileRepository;

    @Autowired
    public TagService(TagRepository tagRepository, PetProfileRepository petProfileRepository) {
        this.tagRepository = tagRepository;
        this.petProfileRepository = petProfileRepository;
    }

    public List<Tag> getTags() {
        return tagRepository.findAll();
    }

    public Tag getTagById(Long id) {
        return tagRepository.findById(id).get();
    }

    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag updateTag(Long id, Tag tag) {
        tag.setId(id);
        return tagRepository.save(tag);
    }

    public void deleteTagById(Long id) {
        tagRepository.deleteById(id);
    }

    public List<Tag> getPetProfileTags(Long petProfileId) {
        PetProfile petProfile = petProfileRepository.findById(petProfileId).get();
        return tagRepository.findByPetProfilesContains(petProfile);
    }
}
