package com.capgemini.NightFox.repository;

import com.capgemini.NightFox.model.Artist_Images;
import com.capgemini.NightFox.model.LostAndFound;
import com.capgemini.NightFox.model.LostAndFound_Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LostAndFound_ImageRepository extends JpaRepository<LostAndFound_Images, Long> {
}
