package com.example.drivercarpaking.models;
public class SeatReponse {
    private Long id;
    private String name;

    public SeatReponse() {
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

    public SeatReponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
