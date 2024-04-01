package com.dburackov.petfiesta.repositories;

import com.dburackov.petfiesta.entities.Like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
}
