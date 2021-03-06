package com.capgemini.NightFox.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class ConcertHall {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message = "Hall name is mandatory.")
    private String hallName;
    @NotBlank(message = "street is mandatory.")
    private String street;
    @NotNull(message = "Number is mandatory.")
    private Integer number;
    @NotBlank(message = "city is mandatory.")
    private String city;

    private String phone;
    private Integer capacity;
    private Boolean openAir;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "concertHall")
    private List<Concert> concerts = new ArrayList<>();

    public ConcertHall() {
    }

    public ConcertHall(String hallName, String street, Integer number, String city, String phone,
                       Integer capacity, Boolean openAir) {
        this.hallName = hallName;
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

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
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
