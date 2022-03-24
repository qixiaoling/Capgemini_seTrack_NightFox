package com.capgemini.NightFox.repository;

import com.capgemini.NightFox.model.Artist;
import com.capgemini.NightFox.model.Concert;
import com.capgemini.NightFox.model.ConcertHall;
import lombok.Data;
import org.assertj.core.api.Assertions;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ConcertRepositoryTest {
    @Autowired
    private ConcertRepository underTest;
    private Concert concert1;
    private Concert concert2;
    private Artist artist;
    private ConcertHall concertHall1;
    private ConcertHall concertHall2;
    @BeforeEach
    void init(){
        artist = new Artist("Xiaoling", "Comes from China.");
        concertHall1 = new ConcertHall("Johan Cruijff ArenA", "AmersterdamStreet", 1, "Amsterdam", "010-12345", 50000, true);
        concertHall2 = new ConcertHall("Stadion Feijenoord", "Van Zandvlietplein ", 1, "Rotterdam", "010-12345", 60000, false);

        concert1 = new Concert(artist, concertHall1);
        concert2 = new Concert(artist, concertHall2);

        underTest.save(concert1);
        underTest.save(concert2);
    }
    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

    @Test
    void findByArtist() {


        List<Concert> list = underTest.findByArtist(artist);
        Assertions.assertThat(list.size()>1);



    }

    @Test
    void findByArtistAndConcertHall() {
      Optional<Concert> possibleConcert = underTest.findByArtistAndConcertHall(artist, concertHall1);
        Assertions.assertThat(possibleConcert).isPresent();

    }

    @Test
    void deleteByArtistAndConcertHall() {
        underTest.deleteByArtistAndConcertHall(artist, concertHall2);
        List<Concert> list = underTest.findByArtist(artist);
        Assertions.assertThat(list.size()).isEqualTo(1);
    }
}