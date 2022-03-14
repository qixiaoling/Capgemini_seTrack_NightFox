package com.capgemini.NightFox.repository;

import com.capgemini.NightFox.model.Concert_Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Concert_HallRepository extends JpaRepository <Concert_Hall, Long> {

    Optional<Concert_Hall> findById(Long id);
    Concert_Hall findConcert_HallById(Long id);
    boolean existsByHallName(String hallName);
}
