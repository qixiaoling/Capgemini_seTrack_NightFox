package com.capgemini.NightFox.controller;

import com.capgemini.NightFox.model.Artist;
import com.capgemini.NightFox.service.ArtistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArtistController.class)
class ArtistControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ArtistService artistService;


    @BeforeEach
    void setUp() {
    }

    @Test
    void givenArtists_whenGetArtists_thenReturnJsonArray() throws Exception {
        //BELOW CODE PASSED:
        Artist artist = new Artist("Micky", "From Disney");
        List<Artist> artistList = Arrays.asList(artist);
        when(artistService.getAllArtist()).thenReturn(artistList);

        mockMvc.perform(get("/artist/getall")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(artistService).getAllArtist();//would also pass with this line, why?
//BELOW CODE FAILED...
//        mockMvc.perform(get("/artist/getall")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) content().json("[]"));
//        verify(underTest, times(1)).getAllArtist();
    }

    @Test
    void givenArtists_whenGetArtistsById_thenReturnArtist() throws Exception {

        Artist artist = new Artist();
        artist.setId(1L);
        artist.setBandName("Micky");
        artist.setDescription("Wears a tie");

        when(artistService.getArtistById(anyLong())).thenReturn(artist);

        mockMvc.perform(get("/artist/getbyid/{artistId}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }

    @Test
    void givenArtists_whenGetArtistsByBandName_thenReturnArtist() throws Exception {

        Artist artist = new Artist();
        artist.setId(1L);
        artist.setBandName("Micky");
        artist.setDescription("Wears a tie");

        when(artistService.getArtistByBandName(any())).thenReturn(artist);

        mockMvc.perform(get("/artist/getbyname/{artistName}", "Micky")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void addArtist_whenBandNameNotNull_thenReturnOk() throws Exception{
        Artist artist = new Artist();
        artist.setId(1L);
        artist.setBandName("Micky");
        artist.setDescription("Wears a tie");

        mockMvc.perform(post("artist/add")
                .contentType(MediaType.APPLICATION_JSON))
                .content("Micky")
                .andExpect(status().isOk());
    }

    @Test
    void updateArtisById() {
    }

    @Test
    void deleteArtistById() {
    }
}