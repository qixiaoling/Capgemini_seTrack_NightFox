package com.capgemini.NightFox.service;

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

@Service
public class ArtistService {

    @Autowired
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

    }





}





