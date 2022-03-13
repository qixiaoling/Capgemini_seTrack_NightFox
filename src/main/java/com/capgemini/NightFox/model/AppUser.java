package com.capgemini.NightFox.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;
    @NotBlank(message = "Username is mandatory")
    @Size(min = 4, message = "Username must be at least of 4 charaters.")
    private String userName;
    @NotBlank(message = "password is mandatory")
    private String password;
    private AppUserGender gender;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(
            joinColumns = {@JoinColumn},
            inverseJoinColumns = {@JoinColumn}
    )
    private Set<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appUser")
    private List<FoodAndBeverage_Order> orders = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "appUser")
    private List<Concert_Ticket> concert_ticketList = new ArrayList<>();

    public AppUser() {
    }

    public AppUser(String email, String userName, String password, AppUserGender gender) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AppUserGender getGender() {
        return gender;
    }

    public void setGender(AppUserGender gender) {
        this.gender = gender;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<FoodAndBeverage_Order> getOrders() {
        return orders;
    }

    public void setOrders(List<FoodAndBeverage_Order> orders) {
        this.orders = orders;
    }

    public List<Concert_Ticket> getConcert_ticketList() {
        return concert_ticketList;
    }

    public void setConcert_ticketList(List<Concert_Ticket> concert_ticketList) {
        this.concert_ticketList = concert_ticketList;
    }
}
