package com.dburackov.petfiesta.mappers;

import com.dburackov.petfiesta.dto.tag.TagDto;
import com.dburackov.petfiesta.entities.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {
    Tag tagDtoToTag(TagDto tagDto);

    TagDto tagToTagDto(Tag tag);
}
