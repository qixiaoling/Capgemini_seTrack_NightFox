package com.capgemini.NightFox.repository;

import com.capgemini.NightFox.model.ConcertHall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConcertHallRepository extends JpaRepository <ConcertHall, Long> {

    //    Optional<ConcertHall> findById(Long id);
//    Optional<ConcertHall> findConcertHallById(Long id);
    boolean existsByHallName(String hallName);
}