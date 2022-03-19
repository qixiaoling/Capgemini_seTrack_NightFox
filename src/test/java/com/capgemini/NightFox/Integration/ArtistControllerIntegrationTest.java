package com.capgemini.NightFox.Integration;

import com.capgemini.NightFox.NightFoxApplication;
import com.capgemini.NightFox.model.Artist;
import com.capgemini.NightFox.repository.ArtistRepository;
import com.capgemini.NightFox.service.ArtistService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.json.JSONException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.WebClientRestTemplateAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.skyscreamer.jsonassert.JSONAssert;

import javax.persistence.GeneratedValue;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NightFoxApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ArtistControllerIntegrationTest {

    @LocalServerPort
    private int port;
    @Autowired
    TestRestTemplate testRestTemplate;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private ArtistService artistService;
    Artist artist;
    Artist artistUpdate;


    HttpHeaders headers = new HttpHeaders();

//    @BeforeEach
//    void init() {
//        artist = new Artist();
//        artist.setId(1L);
//        artist.setBandName("Xiaoling");
//        artist.setDescription("will be a star");
//    }
    @Test
    public void testGetAllArtist() throws Exception {
        artist = new Artist();
        artist.setId(1L);
        artist.setBandName("Xiaoling");
        artist.setDescription("will be a star");
        artistService.addArtist(artist);

//THIS WORKS, BUT IT IS A SIMPLER VERSION WITHOUT CHECKING THE JSON MESSAGE.
//        ResponseEntity<String> response =
//                this.testRestTemplate.getForEntity("http://localhost:" + port + "/artist/getall", String.class);
//        AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);


        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(
                createURLWithPort("/artist/getall"),
                HttpMethod.GET, entity, String.class);
        String expected = "[{id:1, bandName:\"Xiaoling\", description:\"will be a star\"}]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
        AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
    private String createURLWithPort(String url) {
        return "http://localhost:" + port + url;
    }

    @Test
    public void testGetArtistById() throws Exception {
        artist = new Artist();
        artist.setId(1L);
        artist.setBandName("Xiaoling");
        artist.setDescription("will be a star");
        artistService.addArtist(artist);

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(
                createURLWithPort("/artist/getbyid/1"),
                HttpMethod.GET,entity, String.class);
        String expected = "{id:1, bandName:\"Xiaoling\", description:\"will be a star\"}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
        AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testGetArtistByBandName() throws Exception {
        artist = new Artist();
        artist.setId(1L);
        artist.setBandName("Xiaoling");
        artist.setDescription("will be a star");
        artistService.addArtist(artist);

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(
                createURLWithPort("/artist/getbyname/Xiaoling"),
                HttpMethod.GET,entity, String.class);
        String expected = "{id:1, bandName:\"Xiaoling\", description:\"will be a star\"}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
        AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    public void testPostOneArtist() throws Exception {
        artist = new Artist();
        artist.setBandName("Xiaoling");
        artist.setDescription("will be a star");


        HttpEntity<Artist> entity = new HttpEntity<>(artist, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(
                createURLWithPort("/artist/add"),
                HttpMethod.POST, entity, String.class);
        String expected = "The artis is successfully added.";
        AssertionsForClassTypes.assertThat(expected).isEqualTo(response.getBody());
        AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        //THIS DID NOT WORK. CANNOT OPEN H2 DATABASE ON PORT 8080.
//        verify(artistRepository, times(1)).save(artist);

    }

    @Test
    public void testUpdateArtistById() throws Exception {
        artist = new Artist();
        artist.setId(1L);
        artist.setBandName("Xiaoling");
        artist.setDescription("will be a star");
        artistService.addArtist(artist);

        artistUpdate = new Artist();
        artistUpdate.setBandName("Xiaoling");
        artistUpdate.setDescription("will be a star soon");


        HttpEntity<Artist> entity = new HttpEntity<>(artistUpdate, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(
                createURLWithPort("/artist/update/1"),
                HttpMethod.PUT, entity, String.class);
        String expected = "The artis is successfully updated.";
        AssertionsForClassTypes.assertThat(expected).isEqualTo(response.getBody());
        AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    public void testDeleteArtistById() throws Exception {
        artist = new Artist();
        artist.setId(1L);
        artist.setBandName("Xiaoling");
        artist.setDescription("Will be a star");
        artistService.addArtist(artist);

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(
                createURLWithPort("/artist/delete/1"),
                HttpMethod.DELETE, entity, String.class);
        String expected = "The artis is successfully deleted.";
        AssertionsForClassTypes.assertThat(expected).isEqualTo(response.getBody());
        AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


}
