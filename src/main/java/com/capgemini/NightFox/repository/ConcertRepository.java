package com.capgemini.NightFox.repository;

import com.capgemini.NightFox.model.Artist;
import com.capgemini.NightFox.model.Concert;
import com.capgemini.NightFox.model.ConcertHall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConcertRepository extends JpaRepository <Concert, Long> {
    List<Concert> findByArtist(Artist artist);
    Concert findByArtistAndConcertHall(Artist artist, ConcertHall concert_hall);
    void deleteByArtistAndConcertHall(Artist artist, ConcertHall concert_hall);


}
