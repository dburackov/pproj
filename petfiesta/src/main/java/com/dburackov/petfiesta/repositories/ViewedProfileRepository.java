package com.dburackov.petfiesta.repositories;


import com.dburackov.petfiesta.entities.ViewedProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ViewedProfileRepository extends JpaRepository<ViewedProfile, UUID> {
}
