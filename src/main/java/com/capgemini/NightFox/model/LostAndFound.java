package com.capgemini.NightFox.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class LostAndFound {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String description;
    private Boolean fetched;
    @OneToMany (mappedBy = "lostAndFound")
    private List<LostAndFound_Images> lostAndFound_imagesList = new ArrayList<>();

    public LostAndFound() {
    }

    public LostAndFound(String name, String description, Boolean fetched) {
        this.name = name;
        this.description = description;
        this.fetched = fetched;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFetched() {
        return fetched;
    }

    public void setFetched(Boolean fetched) {
        this.fetched = fetched;
    }

    public List<LostAndFound_Images> getLostAndFound_imagesList() {
        return lostAndFound_imagesList;
    }

    public void setLostAndFound_imagesList(List<LostAndFound_Images> lostAndFound_imagesList) {
        this.lostAndFound_imagesList = lostAndFound_imagesList;
    }
}
