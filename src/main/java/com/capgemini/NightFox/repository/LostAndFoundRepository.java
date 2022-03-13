package com.capgemini.NightFox.repository;

import com.capgemini.NightFox.model.LostAndFound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LostAndFoundRepository extends JpaRepository <LostAndFound, Long> {
        LostAndFound findLostAndFoundById(Long id);




}
