package com.dburackov.petfiesta.repositories;

import com.dburackov.petfiesta.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
}
