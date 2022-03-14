package com.capgemini.NightFox.service;

import com.capgemini.NightFox.Exception.BadRequestException;
import com.capgemini.NightFox.Exception.NotFoundException;
import com.capgemini.NightFox.model.Artist;
import com.capgemini.NightFox.repository.ArtistRepository;
import org.apache.coyote.Response;
import org.hibernate.hql.internal.ast.tree.ResolvableNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {


    private ArtistRepository artistRepository;


    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public ResponseEntity<?> getAllArtist () {
        List<Artist> artistList = new ArrayList<>();
        artistRepository.findAll().forEach(artistList::add);
        return ResponseEntity.ok().body(artistList);
    }

    public ResponseEntity<?> addArtist(Artist artist) {
        boolean existsName = artistRepository.existsByBandName(artist.getBandName());
        if(existsName){
            throw new BadRequestException(
                    "Artist name: " + artist.getBandName() + "taken");
        }
        artistRepository.save(artist);
        return ResponseEntity.ok().body("The artist is added.");
    }

    public ResponseEntity<?> getArtistById(Long id) {
        Optional<Artist> possibleArtist = artistRepository.findById(id);
        if(possibleArtist.isPresent()){
           return ResponseEntity.ok().body(possibleArtist.get());
        }
        throw new NotFoundException(
                "Artist id: " + id + "does not exists.");
    }

    public ResponseEntity<?> updateArtisById(Long id, Artist artist) {
        Optional<Artist> possibleArtist = artistRepository.findById(id);
        if(possibleArtist.isPresent()){
            possibleArtist.get().setBandName(artist.getBandName());
            possibleArtist.get().setDescription(artist.getDescription());
            artistRepository.save(possibleArtist.get());
            return ResponseEntity.ok().body("The artist is successfully updated.");
        }
        throw new NotFoundException(
                    "Artist id: " + id + "does not exists.");
    }

    public ResponseEntity<?> deleteArtistById(Long id){
        Optional<Artist> possibleArtist = artistRepository.findById(id);
        if(possibleArtist.isPresent()){
            artistRepository.deleteById(id);
            return ResponseEntity.ok().body("The artist is successfully deleted.");
        }
        throw new NotFoundException(
                "Artist id: " + id + "does not exists.");
    }




}





