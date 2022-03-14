package com.capgemini.NightFox.controller;

import com.capgemini.NightFox.model.Artist;
import com.capgemini.NightFox.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    private ArtistService artistService;
    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllArtist(){

        return artistService.getAllArtist();
    }

    @GetMapping("/getbyid/{artistId}")
    public ResponseEntity<?> getArtistById(@PathVariable("artistId") Long id){

        return artistService.getArtistById(id);
    }
    @GetMapping("/getbyname/{artistName}")
    public ResponseEntity<?> getArtistByBandName(@PathVariable("artistName") String bandName){

        return artistService.getArtistByBandName(bandName);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addArtist(@Valid @RequestBody Artist artist) {

        return artistService.addArtist(artist);
    }
    @PutMapping("/update/{artistId}")
    public ResponseEntity<?> updateArtisById (@PathVariable("artistId") Long id,
                                              @Valid @RequestBody Artist artist){
        return artistService.updateArtisById(id, artist);
    }

    @DeleteMapping("/delete/{artistId}")
    public ResponseEntity<?> deleteArtistById(@PathVariable("artistId") Long id){
        return artistService.deleteArtistById(id);
    }











}
