package com.capgemini.NightFox.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class FoodAndBeverage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private Integer price;
    @ManyToOne
    private FoodAndBeverage_Category foodAndBeverage_category;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "foodAndBeverage")
    private List<FoodAndBeverage_Order> orders = new ArrayList<>();
    @OneToMany(mappedBy = "foodAndBeverage")
    private List<FoodAndBeverage_Images> foodAndBeverage_images = new ArrayList<>();
}
