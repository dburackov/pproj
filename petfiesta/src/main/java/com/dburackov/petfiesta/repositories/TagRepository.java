package com.dburackov.petfiesta.repositories;

import com.dburackov.petfiesta.entities.PetProfile;
import com.dburackov.petfiesta.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByPetProfilesId(Long petProfileId);
}
