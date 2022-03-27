package com.capgemini.NightFox.controller;

import com.capgemini.NightFox.service.Artist_ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/images/")
public class Artist_ImagesController {
    private Artist_ImagesService artist_imagesService;
    @Autowired
    public Artist_ImagesController(Artist_ImagesService artist_imagesService) {
        this.artist_imagesService = artist_imagesService;
    }
    @PostMapping("/add/{artistId}")
    public ResponseEntity<?> addArtist_ImagesToArtist(@PathVariable ("artistId") Long id,
                                                      @RequestParam ("file") MultipartFile file) throws IOException {
        artist_imagesService.addArtist_ImagesToArtist(id, file);
        return ResponseEntity.ok().body("The file has been saved.");
    }



}
