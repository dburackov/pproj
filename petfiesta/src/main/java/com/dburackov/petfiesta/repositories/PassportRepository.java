package com.dburackov.petfiesta.repositories;

import com.dburackov.petfiesta.entities.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Long> {
    Passport findByPetProfileId(Long petProfileId);
}
