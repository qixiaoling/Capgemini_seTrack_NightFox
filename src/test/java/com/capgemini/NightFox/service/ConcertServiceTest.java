package com.capgemini.NightFox.service;

import com.capgemini.NightFox.model.Artist;
import com.capgemini.NightFox.model.Concert;
import com.capgemini.NightFox.model.ConcertHall;
import com.capgemini.NightFox.repository.ArtistRepository;
import com.capgemini.NightFox.repository.ConcertHallRepository;
import com.capgemini.NightFox.repository.ConcertRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class ConcertServiceTest {
    @Mock
    private ConcertRepository concertRepository;
    @Mock
    private ConcertHallRepository concertHallRepository;
    @Mock
    private ArtistRepository artistRepository;
    @InjectMocks
    private ConcertService underTest;
    private Concert concert1;
    private Concert concert2;
    private Artist artist;
    private ConcertHall concertHall1;
    private ConcertHall concertHall2;
    private Concert concert;


    @BeforeEach
    void init() {
        underTest = new ConcertService(artistRepository, concertRepository, concertHallRepository);

        artist = new Artist();
        artist.setId(1L);
        artist.setBandName("Xiaoling");
        artist.setDescription("Coming from China");

        concertHall1 = new ConcertHall();
        concertHall1.setId(2L);
        concertHall1.setHallName("Johan Cruijff ArenA");
        concertHall1.setStreet("AmersterdamStreet");
        concertHall1.setNumber(1);
        concertHall1.setCity("Amsterdam");
        concertHall1.setPhone("030-12345");
        concertHall1.setCapacity(500000);
        concertHall1.setOpenAir(true);
        concertHallRepository.save(concertHall1);
        concertHallRepository.save(concertHall2);

        concertHall2 = new ConcertHall();
        concertHall1.setId(3L);
        concertHall1.setHallName("Stadion Feijenoord");
        concertHall1.setStreet("Van Zandvlietplein");
        concertHall1.setNumber(1);
        concertHall1.setCity("Rotterdam");
        concertHall1.setPhone("020-12345");
        concertHall1.setCapacity(60000);
        concertHall1.setOpenAir(false);

        concert1 = new Concert();
        concert1.setId(4L);
        concert1.setArtist(artist);
        concert1.setConcertHall(concertHall1);
        concert2 = new Concert();
        concert2.setId(5L);
        concert2.setArtist(artist);
        concert2.setConcertHall(concertHall2);

        concertRepository.save(concert1);
        concertRepository.save(concert2);



    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllConcerts() {
        underTest.getAllConcerts();
        verify(concertRepository).findAll();
    }

    @Test
    void ShouldReturnConcertListIfArtistBandNameExists() {

        when(artistRepository.findByBandName("Xiaoling")).thenReturn(Optional.of(artist));
//        when(concertHallRepository.existsById(2L)).thenReturn(true);
//        when(concertHallRepository.existsById(3L)).thenReturn(true);

//       when(concertRepository.findByArtistAndConcertHall(artist, concertHall1)).thenReturn(Optional.of(concert1));
//       when(concertRepository.findByArtistAndConcertHall(artist, concertHall2)).thenReturn(Optional.of(concert2));

//        List<Concert> concertList = underTest.getAllConcertsByArtistBandName("Xiaoling");
//        AssertionsForClassTypes.assertThat(concertList.size()).isEqualTo(2);

        underTest.getAllConcertsByArtistBandName("Xiaoling");
        verify(concertRepository).findByArtist(artist);

    }

    @Test
    void getAllConcertsByArtistId() {
        when(artistRepository.findById(artist.getId())).thenReturn(Optional.of(artist));

        underTest.getAllConcertsByArtistId(artist.getId());
        verify(concertRepository).findByArtist(artist);
    }

    @Test
    void addConcertHallToArtist() {
        Mockito.when(artistRepository.findById(1L)).thenReturn(Optional.of(artist));
        Mockito.when(concertHallRepository.findById(2L)).thenReturn(Optional.of(concertHall1));
        underTest.addConcertHallToArtist(1L, 2L, concert);
        verify(concertRepository).save(concert1);
    }


    @Test
    void deleteConcertById() {

        Mockito.when(artistRepository.findById(1L)).thenReturn(Optional.of(artist));
        Mockito.when(concertHallRepository.findById(2L)).thenReturn(Optional.of(concertHall1));
        Mockito.when(concertRepository.findByArtistAndConcertHall(artist, concertHall1)).thenReturn(Optional.of(concert1));

        underTest.deleteConcertById(1L, 2L);
        verify(concertRepository).deleteByArtistAndConcertHall(artist, concertHall1);
    }
}