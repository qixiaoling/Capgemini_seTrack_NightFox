package com.capgemini.NightFox.controller;

import com.capgemini.NightFox.model.Artist;
import com.capgemini.NightFox.service.ArtistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        //NO NEED FOR ARRAY HERE?
        Artist artist = new Artist("Micky", "From Disney");
        List<Artist> artistList = Arrays.asList(artist);
        when(artistService.getAllArtist()).thenReturn(artistList);
        String expected = "[{bandName:\"Micky\", description:\"From Disney\"}]";

        MvcResult mvcResult = mockMvc.perform(get("/artist/getall")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
//IS THIS A MUST WITHIN CONTROLLER UNIT TEST?
        verify(artistService, times(1)).getAllArtist();
//        AssertionsForClassTypes.assertThat(contentAsString).isEqualTo(expected);
        JSONAssert.assertEquals(expected, contentAsString, false);

    }

    @Test
    void givenArtists_whenGetArtistsById_thenReturnArtist() throws Exception {

//        Artist artist = new Artist();
//        artist.setId(1L);
//        artist.setBandName("Micky");
//        artist.setDescription("Wears a tie");
//
//        when(artistService.getArtistById(anyLong())).thenReturn(artist);

        mockMvc.perform(get("/artist/getbyid/{artistId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }

    @Test
    void givenArtists_whenGetArtistsByBandName_thenReturnArtist() throws Exception {
//NO NEED FOR INSTANCE OF ARTIST?
//        Artist artist = new Artist();
//        artist.setId(1L);
//        artist.setBandName("Micky");
//        artist.setDescription("Wears a tie");
//
//        when(artistService.getArtistByBandName(any())).thenReturn(artist);

        mockMvc.perform(get("/artist/getbyname/{artistName}", "Micky")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void addArtist_whenBandNameNotNull_thenReturnOk() throws Exception {
        mockMvc
                .perform(
                        post("/artist/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"bandName\":\"Micky\", \"description\": \"Wears a tie\"}")

                )
                .andExpect(status().isOk());
    }

    @Test
    void addArtist_WhenBandNameIsNull_thenReturnBadRequest() throws Exception {
        mockMvc
                .perform(
                        post("/artist/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{}")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateArtist_whenIdGiven() throws Exception {
        //WITHOUT THE INSTANCE, IT WORKS TOO, HOW DOES IT KNOW THE ID IS PRESENT?
//        Artist artist = new Artist();
//        artist.setId(1L);
//        artist.setBandName("Micky");
//        artist.setDescription("Wears a tie");

        mockMvc.perform(
                        put("/artist/update/{artistId}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"bandName\":\"Micky\", \"description\": \"Take off the tie\"}")
                )
                .andExpect(status().isOk());

        //CANNOT TEST ARTIST DESCRIPTION?
//        AssertionsForClassTypes.assertThat(artist.getDescription()).isEqualTo("Take off the tie");
    }

    @Test
    void updateArtistFail_whenArtistIsNotGiven() throws Exception {

        //NO NEED TO TEST WHEN ID IS NOT GIVEN?
        mockMvc.perform(
                        put("/artist/update/{artistId}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{}")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteArtist_whenArtistIdIsGiven() throws Exception {
        mockMvc.perform(
                        delete("/artist/delete/{artistId}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{}")
                )
                .andExpect(status().isOk());
    }
}