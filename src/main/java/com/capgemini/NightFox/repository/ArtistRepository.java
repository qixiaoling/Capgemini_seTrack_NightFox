package com.capgemini.NightFox.repository;

import com.capgemini.NightFox.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository <Artist, Long> {
    Optional<Artist> findById(Long id);
    boolean existsByBandName(String name);

}
