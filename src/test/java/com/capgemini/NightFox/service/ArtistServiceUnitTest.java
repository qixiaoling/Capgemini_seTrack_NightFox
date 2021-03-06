package com.capgemini.NightFox.service;

import com.capgemini.NightFox.Exception.BadRequestException;
import com.capgemini.NightFox.Exception.NotFoundException;
import com.capgemini.NightFox.model.Artist;
import com.capgemini.NightFox.repository.ArtistRepository;
import org.assertj.core.api.AssertionsForClassTypes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;


class ArtistServiceUnitTest {


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
    void checkArtistIsExistsByArtistId() {
        Long id = 10L;
        given(artistRepository.existsById(id)).willReturn(false);

        //when
        //then
        AssertionsForClassTypes.assertThatThrownBy(()-> underTest.checkArtistIsExistsByArtistId(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Artist id: " + id + "does not exist.");
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
    void addingShouldThrowWhenArtistBandNameIsTaken() {
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
    @Test
    void gettingShouldReturnArtistIfIdExists(){
        //given
        Artist artist = new Artist();
        artist.setId(1L);
        artist.setBandName("Micky");
        artist.setDescription("Wears a tie");

        when(artistRepository.findById(1L)).thenReturn(Optional.of(artist));
        //when
        underTest.getArtistById(1L);
        //then
        AssertionsForClassTypes.assertThat(underTest.getArtistById(1L)).isEqualTo(artist);
    }

    @Test
    void gettingShouldThrowWhenArtistIdDoesNotExist(){
        //given
        Long id = 10L;
        given(artistRepository.existsById(id)).willReturn(false);

        //when
        //then
        AssertionsForClassTypes.assertThatThrownBy(()-> underTest.getArtistById(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Artist id: " + id + "does not exist.");
    }
    @Test
    void gettingShouldReturnArtistIfBandNameExists(){
        //given
        Artist artist = new Artist();
        artist.setId(1L);
        artist.setBandName("Micky");
        artist.setDescription("Wears a tie");

        when(artistRepository.findByBandName("Micky")).thenReturn(Optional.of(artist));
        //when
        underTest.getArtistByBandName("Micky");

        //then
        AssertionsForClassTypes.assertThat(underTest.getArtistByBandName("Micky")).isEqualTo(artist);

    }

    @Test
    void shouldUpdateIfArtistIdPresent(){
        //given
        Artist artistOld = new Artist();
        artistOld.setId(1L);
        artistOld.setBandName("Micky");
        artistOld.setDescription("Wears a tie");

        Artist artistNew = new Artist();
        artistNew.setBandName("Micky");
        artistNew.setDescription("Takes off the tie");

        Mockito.when(artistRepository.findById(1L)).thenReturn(Optional.of(artistOld));
        underTest.updateArtisById(artistOld.getId(), artistNew);

        AssertionsForClassTypes.assertThat(artistOld.getDescription()).isEqualTo("Takes off the tie");

    }

    @Test
    void updateShouldThrowIfArtistIdNotExists(){
        //given
        Long id = 10L;
        given(artistRepository.existsById(id)).willReturn(false);

        Artist artistNew = new Artist();
        artistNew.setBandName("Micky");
        artistNew.setDescription("Takes off the tie");
        //when
        //then
        AssertionsForClassTypes.assertThatThrownBy(()-> underTest.updateArtisById(id, artistNew))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Artist id: " + id + "does not exist.");
    }

    @Test
    void shouldDeleteIfArtistIdPresent(){

        Artist artist = new Artist();
        artist.setId(1L);
        artist.setBandName("Micky");
        artist.setDescription("Wears a tie");
        when(artistRepository.findById(anyLong())).thenReturn(Optional.of(artist));
        underTest.deleteArtistById(1L);
        verify(artistRepository, times(1)).deleteById(1L);

//        when(artistRepository.existsById(artist.getId())).thenReturn(true);
//        //when
//        underTest.deleteArtistById(artist.getId());
//        //then
////        AssertionsForClassTypes.assertThat(underTest.getArtistById(1L)).isEqualTo(artist);
//        verify(artistRepository).deleteById(artist.getId());

    }

    @Test
    void deleteShouldThrowIfIdNotExist(){
        //given
        Long id = 10L;
        given(artistRepository.existsById(id)).willReturn(false);

        //when
        //then
        AssertionsForClassTypes.assertThatThrownBy(()-> underTest.deleteArtistById(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Artist id: " + id + "does not exist.");
    }
}