package com.dburackov.petfiesta.repositories;

import com.dburackov.petfiesta.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
