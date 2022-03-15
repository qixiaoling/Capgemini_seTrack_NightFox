package com.capgemini.NightFox.repository;

import com.capgemini.NightFox.model.Artist;
import com.capgemini.NightFox.model.Artist_Images;
import com.capgemini.NightFox.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository <Review, Long> {

    Optional<Review> findById(Long id);
    List<Review> findByArtist(Artist artist);



}
