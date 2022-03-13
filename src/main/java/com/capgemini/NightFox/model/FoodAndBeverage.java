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

    public FoodAndBeverage() {
    }

    public FoodAndBeverage(String name, Integer price) {
        this.name = name;
        this.price = price;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public FoodAndBeverage_Category getFoodAndBeverage_category() {
        return foodAndBeverage_category;
    }

    public void setFoodAndBeverage_category(FoodAndBeverage_Category foodAndBeverage_category) {
        this.foodAndBeverage_category = foodAndBeverage_category;
    }

    public List<FoodAndBeverage_Order> getOrders() {
        return orders;
    }

    public void setOrders(List<FoodAndBeverage_Order> orders) {
        this.orders = orders;
    }

    public List<FoodAndBeverage_Images> getFoodAndBeverage_images() {
        return foodAndBeverage_images;
    }

    public void setFoodAndBeverage_images(List<FoodAndBeverage_Images> foodAndBeverage_images) {
        this.foodAndBeverage_images = foodAndBeverage_images;
    }
}
