package com.dburackov.petfiesta.services;

import com.dburackov.petfiesta.entities.PetProfile;
import com.dburackov.petfiesta.entities.Tag;
import com.dburackov.petfiesta.repositories.PetProfileRepository;
import com.dburackov.petfiesta.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private final TagRepository tagRepository;
    private final PetProfileRepository petProfileRepository;

    @Autowired
    public TagService(TagRepository tagRepository,
                      PetProfileRepository petProfileRepository)
    {
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

    public List<Tag> getTagsByPetProfileid(Long petProfileId) {
        return tagRepository.findByPetProfilesId(petProfileId);
    }

    public void setPetProfileTags(Long petProfileId, Tag tag, Long authenticatedUserId) {
        var petProfileOpt = petProfileRepository.findById(petProfileId);
        PetProfile petProfile = null;
        if (petProfileOpt.isPresent()) {
            petProfile = petProfileOpt.get();
        }
        if (petProfile != null) {
            if (!authenticatedUserId.equals(petProfile.getUser().getId())) {
                throw new AccessDeniedException("");
            }
            petProfile.getTags().add(tag);
            petProfileRepository.save(petProfile);
        }
    }
}
