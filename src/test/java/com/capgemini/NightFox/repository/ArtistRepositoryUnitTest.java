package com.capgemini.NightFox.repository;
import com.capgemini.NightFox.model.Artist;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
class ArtistRepositoryUnitTest {
    @Autowired
    private ArtistRepository underTest;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

    @Test
    void findByBandName() {
    }

    @Test
    void itShouldCheckIfArtistBandNameExists() {
        //given
        Artist artist = new Artist("Xiaoling", "Comes from China.");
        underTest.save(artist);

        //when
        boolean expected = underTest.existsByBandName("Xiaoling");

        //then
        Assertions.assertTrue(expected);
    }

    @Test
    void returnFalseIfArtistBandNameNotExist(){
        //given
        String bandName = "Xiaoling";

        //when
        boolean expected = underTest.existsByBandName("Xiaoling");

        //then
        Assertions.assertFalse(expected);

    }


}