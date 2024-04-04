package com.dburackov.petfiesta.repositories;

import com.dburackov.petfiesta.entities.SocialMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SocialMediaRepository extends JpaRepository<SocialMedia, Long> {
    List<SocialMedia> findByPetProfileId(Long id);
}
