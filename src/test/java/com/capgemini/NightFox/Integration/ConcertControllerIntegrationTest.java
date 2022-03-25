package com.capgemini.NightFox.Integration;

import com.capgemini.NightFox.NightFoxApplication;
import com.capgemini.NightFox.model.Artist;
import com.capgemini.NightFox.model.Concert;
import com.capgemini.NightFox.model.ConcertHall;
import com.capgemini.NightFox.repository.ArtistRepository;
import com.capgemini.NightFox.repository.ConcertHallRepository;
import com.capgemini.NightFox.repository.ConcertRepository;
import com.capgemini.NightFox.service.ArtistService;
import com.capgemini.NightFox.service.ConcertHallService;
import com.capgemini.NightFox.service.ConcertService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NightFoxApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ConcertControllerIntegrationTest {
    @LocalServerPort
    private int port;
    @Autowired
    TestRestTemplate testRestTemplate;
    @Autowired
    private ConcertRepository concertRepository;
    @Autowired
    private ConcertService concertService;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private ArtistService artistService;
    @Autowired
    private ConcertHallRepository concertHallRepository;
    @Autowired
    private ConcertHallService concertHallService;

    private Concert concert1;
    private Concert concert2;
    private Artist artist;
    private ConcertHall concertHall1;
    private ConcertHall concertHall2;
    private Concert concert;

    HttpHeaders headers = new HttpHeaders();

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
        
        concert1 = new Concert(200.00, "Xiang shang kan", LocalDate.of(2022, 01, 01) );


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetAllConcerts() throws Exception {

        artistService.addArtist(artist);
        concertHallService.addConcertHall(concertHall1);
        concertService.addConcertHallToArtist(artist.getId(), concertHall1.getId(), concert1);

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(
                createURLWithPort("/concert/getall"),
                HttpMethod.GET, entity, String.class);
        String expected =  "[{id: 3, price: 200.00, description: \"Xiang shang kan\", time: \"01/01/2022\", artist: {id:1,bandName:\"Xiaoling\", description: \"Coming from China\"}, concertHall: {hallName: \"Johan Cruijff ArenA\", street: \"AmersterdamStreet\", number: 1,city: \"Amsterdam\", phone: \"030-12345\"," +
                "capacity: 500000, openAir: true}}]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
        AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
    private String createURLWithPort(String url) {
        return "http://localhost:" + port + url;
    }

    @Test
    void testGetAllConcertsByArtistId() throws Exception {
        artistService.addArtist(artist);
        concertHallService.addConcertHall(concertHall1);
        concertService.addConcertHallToArtist(artist.getId(), concertHall1.getId(), concert1);

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(
                createURLWithPort("/concert/getbyid/1"),
                HttpMethod.GET,entity, String.class);
        String expected =  "[{id: 3, price: 200.00, description: \"Xiang shang kan\", time: \"01/01/2022\", artist: {id:1,bandName:\"Xiaoling\", description: \"Coming from China\"}, concertHall: {hallName: \"Johan Cruijff ArenA\", street: \"AmersterdamStreet\", number: 1,city: \"Amsterdam\", phone: \"030-12345\"," +
                "capacity: 500000, openAir: true}}]";
        JSONAssert.assertEquals(expected, response.getBody(), false);
        AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testGetAllConcertsByArtistBandName() throws Exception{
        artistService.addArtist(artist);
        concertHallService.addConcertHall(concertHall1);
        concertService.addConcertHallToArtist(artist.getId(), concertHall1.getId(), concert1);

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(
                createURLWithPort("/concert/getbyname/Xiaoling"),
                HttpMethod.GET,entity, String.class);
        String expected =  "[{id: 3, price: 200.00, description: \"Xiang shang kan\", time: \"01/01/2022\", artist: {id:1,bandName:\"Xiaoling\", description: \"Coming from China\"}, concertHall: {hallName: \"Johan Cruijff ArenA\", street: \"AmersterdamStreet\", number: 1,city: \"Amsterdam\", phone: \"030-12345\"," +
                "capacity: 500000, openAir: true}}]";
        JSONAssert.assertEquals(expected, response.getBody(), false);
        AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testAddConcertHallToArtist() throws Exception {
        artistService.addArtist(artist);
        concertHallService.addConcertHall(concertHall1);

        HttpEntity<Concert> entity = new HttpEntity<>(concert1, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(
                createURLWithPort("/concert/addconcert/1/2"),
                HttpMethod.POST, entity, String.class);
        String expected = "The Concert is added to the artist.";
        AssertionsForClassTypes.assertThat(expected).isEqualTo(response.getBody());
        AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void deleteConcertById() {
        artistService.addArtist(artist);
        concertHallService.addConcertHall(concertHall1);
        concertService.addConcertHallToArtist(artist.getId(), concertHall1.getId(), concert1);

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(
                createURLWithPort("/concert/delete/1/2"),
                HttpMethod.DELETE, entity, String.class);
        String expected = "The concert is successfully deleted.";
        AssertionsForClassTypes.assertThat(expected).isEqualTo(response.getBody());
        AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}