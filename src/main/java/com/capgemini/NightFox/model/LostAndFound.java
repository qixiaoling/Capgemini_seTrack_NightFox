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
}
