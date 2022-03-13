package com.capgemini.NightFox.repository;

import com.capgemini.NightFox.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository <Artist, Long> {
    Artist findArtistById(Long id);

}
