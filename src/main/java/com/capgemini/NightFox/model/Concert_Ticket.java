package com.capgemini.NightFox.model;

import javax.persistence.*;
import java.sql.SQLTransactionRollbackException;

@Entity
@Table
public class Concert_Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ticket_primary_id")
    private Long id;
    @Column
    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Concert concert;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private AppUser appUser;

    public Concert_Ticket() {
    }

    public Concert_Ticket(Concert concert, AppUser appUser) {
        this.concert = concert;
        this.appUser = appUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Concert getConcert() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
