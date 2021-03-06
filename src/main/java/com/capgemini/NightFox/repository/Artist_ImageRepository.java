package com.capgemini.NightFox.repository;

import com.capgemini.NightFox.model.Artist_Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Artist_ImageRepository extends JpaRepository<Artist_Images, Long> {
    Optional<Artist_Images> findById(Long id);

}
