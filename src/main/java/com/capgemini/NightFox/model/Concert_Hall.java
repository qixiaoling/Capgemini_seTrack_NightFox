package com.capgemini.NightFox.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Concert_Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank
    private String street;
    @NotBlank
    private int number;
    @NotBlank
    private String city;

    private String phone;
    private String capacity;
    private Boolean openAir;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "concert_hall")
    private List<Concert> concerts = new ArrayList<>();

    public Concert_Hall() {
    }

    public Concert_Hall(String street, int number, String city, String phone, String capacity, Boolean openAir) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.phone = phone;
        this.capacity = capacity;
        this.openAir = openAir;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public Boolean getOpenAir() {
        return openAir;
    }

    public void setOpenAir(Boolean openAir) {
        this.openAir = openAir;
    }

    public List<Concert> getConcerts() {
        return concerts;
    }

    public void setConcerts(List<Concert> concerts) {
        this.concerts = concerts;
    }
}
