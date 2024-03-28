package com.dburackov.petfiesta.repositories;

import com.dburackov.petfiesta.entities.PetProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PetProfileRepository extends JpaRepository<PetProfile, UUID> {
}
