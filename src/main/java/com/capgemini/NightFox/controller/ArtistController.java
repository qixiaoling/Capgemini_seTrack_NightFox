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

        return ResponseEntity.ok().body(artistService.getAllArtist());
    }

    @GetMapping("/getbyid/{artistId}")
    public ResponseEntity<?> getArtistById(@PathVariable("artistId") Long id){

        return ResponseEntity.ok().body(artistService.getArtistById(id));
    }
    @GetMapping("/getbyname/{artistName}")
    public ResponseEntity<?> getArtistByBandName(@PathVariable("artistName") String bandName){

        return ResponseEntity.ok().body(artistService.getArtistByBandName(bandName));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addArtist(@Valid @RequestBody Artist artist) {

        return ResponseEntity.ok().body("The artis is successfully added.");
    }
    @PutMapping("/update/{artistId}")
    public ResponseEntity<?> updateArtisById (@PathVariable("artistId") Long id,
                                              @Valid @RequestBody Artist artist){
        return ResponseEntity.ok().body("The artis is successfully updated.");
    }

    @DeleteMapping("/delete/{artistId}")
    public ResponseEntity<?> deleteArtistById(@PathVariable("artistId") Long id){
        return ResponseEntity.ok().body("The artis is successfully deleted.");
    }











}
