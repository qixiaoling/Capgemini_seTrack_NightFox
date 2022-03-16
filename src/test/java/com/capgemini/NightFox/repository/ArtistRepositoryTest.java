package com.capgemini.NightFox.repository;
import com.capgemini.NightFox.model.Artist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
class ArtistRepositoryTest {
    @Autowired
    private ArtistRepository underTest;


    @Test
    void findByBandName() {
    }

    @Test
    void itShouldCheckIfArtistBandNameExists() {
        //given
        Artist artist = new Artist("Xiaoling", "Comes from China.");
        underTest.save(artist);

        //when
        Boolean exists = underTest.existsByBandName("Xiaoling");

        //then
        Assertions.assertTrue(exists);
    }


}