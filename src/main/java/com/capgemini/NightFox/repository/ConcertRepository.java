package com.capgemini.NightFox.repository;

import com.capgemini.NightFox.model.Artist;
import com.capgemini.NightFox.model.Concert;
import com.capgemini.NightFox.model.Concert_Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConcertRepository extends JpaRepository <Concert, Long> {
    List<Concert> findByArtist(Artist artist);
    Concert findByArtistAndConcert_Hall(Artist artist, Concert_Hall concert_hall);
    void deleteByArtistAndConcert_Hall(Artist artist, Concert_Hall concert_hall);
//    void deleteConcertByArtistAndConcert_Hall(Artist artist, Concert_Hall concert_hall);

}
