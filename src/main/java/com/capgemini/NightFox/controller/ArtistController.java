package com.capgemini.NightFox.controller;

import com.capgemini.NightFox.model.Artist;
import com.capgemini.NightFox.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class ArtistController {

    private ArtistService artistService;
    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/getting")
    public ResponseEntity<?> getAllArtist(){
        return artistService.getAllArtist();
    }

    @PostMapping("/adding")
    public ResponseEntity<?> addArtist(@Valid @RequestBody Artist artist) {
        return artistService.addArtist(artist);
    }
}
