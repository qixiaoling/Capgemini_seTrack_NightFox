package com.capgemini.NightFox.repository;

import com.capgemini.NightFox.model.Artist_Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Artist_CategoryRepository extends JpaRepository <Artist_Category, String> {
    Artist_Category findByName(String name);
    boolean existsByName(String name);
}
