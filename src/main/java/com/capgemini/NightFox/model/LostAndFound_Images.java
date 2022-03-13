package com.capgemini.NightFox.model;

import javax.persistence.*;

@Entity
@Table
public class LostAndFound_Images {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String type;
    @Lob
    private byte[] data;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private LostAndFound lostAndFound;

    public LostAndFound_Images() {
    }

    public LostAndFound_Images(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public LostAndFound getLostAndFound() {
        return lostAndFound;
    }

    public void setLostAndFound(LostAndFound lostAndFound) {
        this.lostAndFound = lostAndFound;
    }
}
