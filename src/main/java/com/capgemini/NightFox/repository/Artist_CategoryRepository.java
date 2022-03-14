package com.capgemini.NightFox.repository;

import com.capgemini.NightFox.model.Artist_Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Artist_CategoryRepository extends JpaRepository <Artist_Category, String> {
    Optional<Artist_Category> findByName(String name);
    boolean existsByName(String name);

    void deleteByName(String name);
}
