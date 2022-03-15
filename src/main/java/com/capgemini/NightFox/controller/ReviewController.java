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

        return reviewService.getAllReviews();
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable("id") Long id){

        return reviewService.getReviewById(id);
    }
    @GetMapping("/getbyid/{artistId}")
    public ResponseEntity<?> getReviewsByArtistId(@PathVariable("artistId") Long id){

        return reviewService.getReviewsByArtistId(id);
    }
    @PostMapping("/add/{artistId}")
    public ResponseEntity<?> addReviewToArtist(@PathVariable("artistId") Long artistId,
                                               @Valid @RequestBody Review review) {

        return reviewService.addReviewToArtist(artistId, review);
    }
    @PutMapping("/update/{reviewId}")
    public ResponseEntity<?> updateReviewById (@PathVariable("reviewId") Long reviewId,
                                              @Valid @RequestBody Review review){
        return reviewService.updateReviewById(reviewId, review);
    }
    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<?> deleteReviewById(@PathVariable("reviewId") Long reviewId){
        return reviewService.deleteReviewById(reviewId);
    }





}
