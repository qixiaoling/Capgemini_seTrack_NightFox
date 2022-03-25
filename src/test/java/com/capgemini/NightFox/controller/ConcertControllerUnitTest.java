package com.capgemini.NightFox.controller;

import com.capgemini.NightFox.model.Artist;
import com.capgemini.NightFox.model.Concert;
import com.capgemini.NightFox.model.ConcertHall;
import com.capgemini.NightFox.service.ConcertService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConcertController.class)
class ConcertControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ConcertService concertService;
    private Concert concert1;
    private Concert concert2;
    private Artist artist;
    private ConcertHall concertHall1;
    private ConcertHall concertHall2;
    private Concert concert;


    @BeforeEach
    void setUp() {
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


        concertHall2 = new ConcertHall();
        concertHall2.setId(3L);
        concertHall2.setHallName("Stadion Feijenoord");
        concertHall2.setStreet("Van Zandvlietplein");
        concertHall2.setNumber(1);
        concertHall2.setCity("Rotterdam");
        concertHall2.setPhone("020-12345");
        concertHall2.setCapacity(60000);
        concertHall2.setOpenAir(false);

        concert1 = new Concert(artist, concertHall1);
        concert1.setPrice(200.00);
        concert1.setDescription("Xiang shang kan");
        concert1.setTime(LocalDate.of(2022, 01, 01));


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void givenConcerts_whenGetConcerts_thenReturnJsonArray() throws  Exception{
        List<Concert> concertList =  Arrays.asList(concert1);
        when(concertService.getAllConcerts()).thenReturn(concertList);
        String expected =  "[{id: null, price: 200.00, description: \"Xiang shang kan\", time: \"01/01/2022\", artist: {id:1,bandName:\"Xiaoling\", description: \"Coming from China\"}, concertHall: {hallName: \"Johan Cruijff ArenA\", street: \"AmersterdamStreet\", number: 1,city: \"Amsterdam\", phone: \"030-12345\"," +
                "capacity: 500000, openAir: true}}]";
        MvcResult mvcResult = mockMvc.perform(get("/concert/getall")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        verify(concertService, times(1)).getAllConcerts();
        JSONAssert.assertEquals(expected, contentAsString, false);


    }

    @Test
    void  givenConcerts_whenGetConcertsByArtisId_thenReturnConcerts() throws Exception{
        mockMvc.perform(get("/concert/getbyid/{artistId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void givenConcerts_whenGetAllConcertsByArtistBandName_thenReturnConcerts() throws Exception {
        mockMvc.perform(get("/concert/getbyname/{artistName}", "Xiaoling")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void addConcertHallToArtist_whenBothIdAreGiven_returnStatusOk() throws Exception {
        mockMvc
                .perform(
                        post("/concert/addconcert/{artistId}/{concerthallId}", 1L, 2L)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }
//    @Test
//    void updateConcertById_whenNewConcertIsGiven_returnStatusOk() throws Exception {
//        mockMvc
//                .perform(
//                        put("/concert/updateconcertinfo/{artistId}/{concerthallId}", 1L, 2L)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content("{\"price\":1000.00, \"description\": \"2022\", \"time\": \"01/01/2022\"}")
//                )
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void updateConcertById_whenNewConcertIsNull_returnBadRequest() throws Exception {
//        //status is 200???
//        mockMvc
//                .perform(
//                        put("/concert/updateconcertinfo/{artistId}/{concerthallId}", 1L, 2L)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content("{}")
//                )
//                .andExpect(status().isBadRequest());
//    }

    @Test
    void deleteConcertById_whenArtisIdAndConcertHallIdAreGiven() throws  Exception{
        mockMvc.perform(
                        delete("/concert/delete/{artistId}/{concerthallId}", 1L, 2L)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{}")
                )
                .andExpect(status().isOk());
    }
}