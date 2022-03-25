package com.capgemini.NightFox.service;

import com.capgemini.NightFox.Exception.NotFoundException;
import com.capgemini.NightFox.model.Artist;
import com.capgemini.NightFox.model.Artist_Images;
import com.capgemini.NightFox.repository.ArtistRepository;
import com.capgemini.NightFox.repository.Artist_ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class Artist_ImagesService {
    private Artist_ImageRepository artist_imageRepository;
    private ArtistRepository artistRepository;
    @Autowired
    public Artist_ImagesService(Artist_ImageRepository artist_imageRepository, ArtistRepository artistRepository) {
        this.artist_imageRepository = artist_imageRepository;
        this.artistRepository = artistRepository;
    }

    public ResponseEntity<?> addArtist_ImagesToArtist(Long artistId, MultipartFile file) throws IOException{
        try{
            Optional<Artist> possibleArtist = artistRepository.findById(artistId);
            if(possibleArtist.isPresent()){
                String fileName = file.getOriginalFilename();
                Artist_Images images = new Artist_Images(fileName, file.getContentType(), file.getBytes());
                images.setArtist(possibleArtist.get());
                artist_imageRepository.save(images);
            }
            throw new NotFoundException(
                    "Artis id: " + artistId + "does not exists.");
        }catch(IOException e){
            return ResponseEntity.badRequest().body("The file does not exist.");
        }
    }

    public ResponseEntity<?> getArtist_ImageById(Long imageId) {
        Optional <Artist_Images> possibleImage = artist_imageRepository.findById(imageId);
        if(possibleImage.isPresent()){
            return ResponseEntity.ok().body(possibleImage.get());
        }
        throw new NotFoundException(
                "Image id: " + imageId + "does not exists.");
    }
    public ResponseEntity<?> getImageByArtistId(Long artistId){
        Optional <Artist> possibleArtist = artistRepository.findById(artistId);
        if(possibleArtist.isPresent()){
            try{
                List<Artist_Images> images = possibleArtist.get().getArtist_imagesList();
                return ResponseEntity.ok().body(images);
            }
            catch(NullPointerException e){
                return ResponseEntity.badRequest().body("There are no artist images available to this artist.");
            }

        }
        throw new NotFoundException(
                "Artist id: " + artistId + "does not exist.");
    }




}



