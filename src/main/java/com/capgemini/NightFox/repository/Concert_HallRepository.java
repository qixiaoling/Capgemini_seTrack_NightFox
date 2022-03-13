package com.capgemini.NightFox.repository;

import com.capgemini.NightFox.model.Concert_Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Concert_HallRepository extends JpaRepository <Concert_Hall, Long> {

    Concert_Hall findConcert_HallById(Long id);
}
