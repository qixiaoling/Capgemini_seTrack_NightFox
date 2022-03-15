package com.capgemini.NightFox.service;

import com.capgemini.NightFox.Exception.BadRequestException;
import com.capgemini.NightFox.Exception.NotFoundException;
import com.capgemini.NightFox.model.Artist;
import com.capgemini.NightFox.model.Artist_Category;
import com.capgemini.NightFox.repository.ArtistRepository;
import com.capgemini.NightFox.repository.Artist_CategoryRepository;
import org.apache.coyote.Response;
import org.hibernate.hql.internal.ast.tree.ResolvableNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.geom.AffineTransform;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {


    private ArtistRepository artistRepository;
    private Artist_CategoryRepository artist_categoryRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository, Artist_CategoryRepository artist_categoryRepository) {
        this.artistRepository = artistRepository;
        this.artist_categoryRepository = artist_categoryRepository;
    }



    public List<Artist> getAllArtist () {
        List<Artist> artistList = new ArrayList<>();
        artistRepository.findAll().forEach(artistList::add);
        return artistList;
    }

    public void addArtist(Artist artist) {
        boolean existsName = artistRepository.existsByBandName(artist.getBandName());
        if(existsName){
            throw new BadRequestException(
                    "Artist name: " + artist.getBandName() + "taken");
        }
        artistRepository.save(artist);

    }

//    public ResponseEntity<?> addNewArtistToArtist_Category(String categoryName, Artist artist){
//        Optional <Artist_Category> categoryDB = artist_categoryRepository.findByName(categoryName);
//        if(categoryDB.isPresent()){
//            artist.setArtist_category(categoryDB.get());
//            artistRepository.save(artist);
//            return ResponseEntity.ok().body(" Artist is successfully added into this category.");
//        }
//        throw new NotFoundException(
//                "Category name: " + categoryName + "does not exist.");
//    }

    public Artist getArtistById(Long id) {
        Optional<Artist> possibleArtist = artistRepository.findById(id);
        if(possibleArtist.isPresent()){
           return possibleArtist.get();
        }
        throw new NotFoundException(
                "Artist id: " + id + "does not exist.");
    }
    public Artist getArtistByBandName(String bandName) {
        Optional<Artist> possibleArtist = artistRepository.findByBandName(bandName);
        if(possibleArtist.isPresent()){
            return possibleArtist.get();
        }
        throw new NotFoundException(
                "Artist name: " + bandName + "does not exist.");
    }

    public void updateArtisById(Long id, Artist artist) {
        Optional<Artist> possibleArtist = artistRepository.findById(id);
        if(possibleArtist.isPresent()){
            possibleArtist.get().setBandName(artist.getBandName());
            possibleArtist.get().setDescription(artist.getDescription());
            artistRepository.save(possibleArtist.get());
            return;
        }
        throw new NotFoundException(
                    "Artist id: " + id + "does not exist.");
    }

    public void deleteArtistById(Long id){
        Optional<Artist> possibleArtist = artistRepository.findById(id);
        if(possibleArtist.isPresent()){
            artistRepository.deleteById(id);
        }
        throw new NotFoundException(
                "Artist id: " + id + "does not exist.");
    }




}





