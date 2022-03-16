package com.capgemini.NightFox.service;

import com.capgemini.NightFox.Exception.BadRequestException;
import com.capgemini.NightFox.model.Artist;
import com.capgemini.NightFox.repository.ArtistRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;


class ArtistServiceTest {


    private ArtistRepository artistRepository = Mockito.mock(ArtistRepository.class);

    private ArtistService underTest;

    @BeforeEach
    void setUp() {
        System.out.println("artist repo is: "+ artistRepository);
        underTest = new ArtistService(artistRepository);
    }


    @Test
    void getAllArtist() {
        //when
        underTest.getAllArtist();

        //then
        verify(artistRepository).findAll();
    }

    @Test
    void canAddArtist() {
        //given
        Artist artist = new Artist("Micky", "Comes from Disney");

        Mockito.when(artistRepository.existsByBandName(anyString())).thenReturn(false);
        //when
        underTest.addArtist(artist);
        //then
        verify(artistRepository).save(artist);


//        underTest.addArtist(artist);

//        ArgumentCaptor<Artist> artistArgumentCaptor = ArgumentCaptor.forClass(Artist.class);
//        verify(artistRepository).save(artistArgumentCaptor.capture());
//        Artist capturedArtist = artistArgumentCaptor.getValue();
//        assertThat(capturedArtist).isEqualTo(artist);
    }

    @Test
    void willThrowWhenArtistBandNameIsTaken() {
        //given
        Artist artist = new Artist("Minnie", "Loves pink");
//        BDDMockito.given(artistRepository.existsByBandName(anyString())).willReturn(true);
        Mockito.when(artistRepository.existsByBandName(anyString())).thenReturn(true);

        //when//then
        AssertionsForClassTypes.assertThatThrownBy(() -> underTest.addArtist((artist)))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Artist name: " + artist.getBandName() + "taken");
        verify(artistRepository, never()).save(artist);


    }
}