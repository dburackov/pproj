package com.dburackov.petfiesta.repositories;

import com.dburackov.petfiesta.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByPetProfileId(Long id);
}
