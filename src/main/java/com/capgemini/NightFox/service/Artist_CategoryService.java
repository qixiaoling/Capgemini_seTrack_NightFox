package com.capgemini.NightFox.service;

import com.capgemini.NightFox.Exception.BadRequestException;
import com.capgemini.NightFox.Exception.NotFoundException;
import com.capgemini.NightFox.model.Artist;
import com.capgemini.NightFox.model.Artist_Category;
import com.capgemini.NightFox.repository.Artist_CategoryRepository;
import jdk.jfr.Category;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Artist_CategoryService {
    private Artist_CategoryRepository artist_categoryRepository;
    @Autowired
    public Artist_CategoryService(Artist_CategoryRepository artist_categoryRepository) {
        this.artist_categoryRepository = artist_categoryRepository;
    }

    public ResponseEntity<?> getAllCategory(){
        List<Artist_Category> artist_categories = new ArrayList<>();
        artist_categoryRepository.findAll().forEach(artist_categories::add);
        return ResponseEntity.ok().body(artist_categories);
    }

    public ResponseEntity<?> addCategory(Artist_Category artist_category) {
        boolean existName = artist_categoryRepository.existsByName(artist_category.getName());
        if(existName) {
            throw new BadRequestException(
                    "Artist category: " + artist_category.getName() + "taken.");
        }
        artist_categoryRepository.save(artist_category);
        return ResponseEntity.ok().body("This artist category is added.");
    }
    public ResponseEntity<?> getArtist_CategoryByName(String name) {
        Optional<Artist_Category> possibleArtist_Category = artist_categoryRepository.findByName(name);
        if(possibleArtist_Category.isPresent()){
            return ResponseEntity.ok().body(possibleArtist_Category.get());
        }
        throw new NotFoundException(
                "Artist category name: " + name + "does not exists.");
    }

    public ResponseEntity<?> updateArtist_CategoryByName(String name, Artist_Category  category){
        Optional<Artist_Category> possibleArtist_Category = artist_categoryRepository.findByName(name);
        if(possibleArtist_Category.isPresent()){
            possibleArtist_Category.get().setName(category.getName());
            artist_categoryRepository.save(possibleArtist_Category.get());
            return ResponseEntity.ok().body("The artist category is successfully updated.");
        }
        throw new NotFoundException(
                "Category name: " + category.getName() + "does not exists.");
    }

    public ResponseEntity<?> deleteArtist_CategoryByName(String name) {
        Optional<Artist_Category> possibleArtist_Category = artist_categoryRepository.findByName(name);
        if(possibleArtist_Category.isPresent()){
            artist_categoryRepository.deleteByName(name);
            return ResponseEntity.ok().body("The artist category is successfully deleted.");
        }
        throw new NotFoundException(
                 "Artist category name: " + name + "does not exists.");
    }




}
