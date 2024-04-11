package com.dburackov.petfiesta.services;

import com.dburackov.petfiesta.constants.Constants;
import com.dburackov.petfiesta.dto.tag.ModifyTagDto;
import com.dburackov.petfiesta.dto.tag.TagDto;
import com.dburackov.petfiesta.entities.PetProfile;
import com.dburackov.petfiesta.entities.Tag;
import com.dburackov.petfiesta.exceptions.NotFoundException;
import com.dburackov.petfiesta.mappers.TagMapper;
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
    private final TagMapper tagMapper;

    @Autowired
    public TagService(TagRepository tagRepository,
                      PetProfileRepository petProfileRepository,
                      TagMapper tagMapper)
    {
        this.tagRepository = tagRepository;
        this.petProfileRepository = petProfileRepository;
        this.tagMapper = tagMapper;
    }

    public List<TagDto> getTags() {
        return tagRepository.findAll().stream().map(tagMapper::tagToTagDto).toList();
    }

    public TagDto getTagById(Long id) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        return tagMapper.tagToTagDto(tag);
    }

    public TagDto createTag(TagDto tagDto) {
        Tag tag = tagMapper.tagDtoToTag(tagDto);
        return tagMapper.tagToTagDto(tagRepository.save(tag));
    }

    public TagDto updateTag(Long id, TagDto tagDto) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));
        tag.setName(tagDto.getName());
        return tagMapper.tagToTagDto(tagRepository.save(tag));
    }

    public void deleteTagById(Long id) {
        tagRepository.deleteById(id);
    }

    public List<TagDto> getTagsByPetProfileId(Long petProfileId) {
        return tagRepository.findByPetProfilesId(petProfileId).stream().map(tagMapper::tagToTagDto).toList();
    }

    public void addPetProfileTags(ModifyTagDto modifyTagDto, Long authenticatedUserId) {
        PetProfile petProfile = petProfileRepository.findById(modifyTagDto.getPetProfileId()).orElseThrow(() ->
                new NotFoundException(Constants.NO_SUCH_ENTITY));

        if (!authenticatedUserId.equals(petProfile.getUser().getId())) {
            throw new AccessDeniedException("");
        }

        Tag tag = tagRepository.findById(modifyTagDto.getTagId()).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        tag.getPetProfiles().add(petProfile);
        tagRepository.save(tag);
    }

    public void deletePetProfileTag(ModifyTagDto modifyTagDto, Long authenticatedUserId) {
        PetProfile petProfile = petProfileRepository.findById(modifyTagDto.getPetProfileId()).orElseThrow(() ->
                new NotFoundException(Constants.NO_SUCH_ENTITY));

        if (!authenticatedUserId.equals(petProfile.getUser().getId())) {
            throw new AccessDeniedException("");
        }

        Tag tag = tagRepository.findById(modifyTagDto.getTagId()).orElseThrow(() -> new NotFoundException(Constants.NO_SUCH_ENTITY));

        tag.getPetProfiles().remove(petProfile);
        tagRepository.save(tag);
    }
}
