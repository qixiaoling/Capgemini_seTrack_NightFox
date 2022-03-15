package com.capgemini.NightFox.controller;

import com.capgemini.NightFox.model.Artist;
import com.capgemini.NightFox.model.Review;
import com.capgemini.NightFox.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping("/getall")
    public ResponseEntity<?> getAllReviews(){

        return ResponseEntity.ok().body(reviewService.getAllReviews());
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable("id") Long id){

        return ResponseEntity.ok().body(reviewService.getReviewById(id));
    }
    @GetMapping("/getbyArtistId/{artistId}")
    public ResponseEntity<?> getReviewsByArtistId(@PathVariable("artistId") Long id){

        return ResponseEntity.ok().body(reviewService.getReviewsByArtistId(id));
    }
    @PostMapping("/add/{artistId}")
    public ResponseEntity<?> addReviewToArtist(@PathVariable("artistId") Long artistId,
                                               @Valid @RequestBody Review review) {

        reviewService.addReviewToArtist(artistId, review);
        return ResponseEntity.ok().body("The review is successfully added.");
    }
    @PutMapping("/update/{reviewId}")
    public ResponseEntity<?> updateReviewById (@PathVariable("reviewId") Long reviewId,
                                              @Valid @RequestBody Review review){
        reviewService.updateReviewById(reviewId, review);
        return ResponseEntity.ok().body("The review is successfully updated.");
    }
    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<?> deleteReviewById(@PathVariable("reviewId") Long reviewId){
        reviewService.deleteReviewById(reviewId);
        return ResponseEntity.ok().body("The review is successfully deleted.");
    }





}
