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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.skyscreamer.jsonassert.JSONAssert;

import java.net.URI;
import java.util.List;

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

//        ResponseEntity<String> response =
//                this.testRestTemplate.getForEntity("http://localhost:" + port + "/artist/getall", String.class);
//        AssertionsForClassTypes.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);


        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(
                createURLWithPort("/artist/getall"),
                HttpMethod.GET, entity, String.class);
        String expected = "{id:\"1L\", bandName:\"Xiaoling\", description:\"Will be a star\"}";

        JSONAssert.assertEquals(expected, response.getBody(), false);

//        JSONAssert.assertEquals(expected, response.getBody().get(0), false);
//        AssertionsForClassTypes.assertThat(response.getBody().get(0)).isEqualTo(artist);

//        Assertions.assertEquals("[{id: 1, bandName: Xiaoling, description: will be a star}]", response.getBody());


    }
    private String createURLWithPort(String url) {
        return "http://localhost:" + port + url;
    }

}
