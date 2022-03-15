package com.capgemini.NightFox.repository;

import com.capgemini.NightFox.model.Artist;
import com.capgemini.NightFox.model.Concert;
import com.capgemini.NightFox.model.ConcertHall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConcertRepository extends JpaRepository <Concert, Long> {
    List<Concert> findByArtist(Artist artist);
    Optional<Concert> findByArtistAndConcertHall(Artist artist, ConcertHall concertHall);
    void deleteByArtistAndConcertHall(Artist artist, ConcertHall concertHall);


}
