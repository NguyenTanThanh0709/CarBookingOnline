package com.example.drivercarpaking.models;

import java.time.LocalTime;

public class LocationDetailReponse {
    private Long id;
    private String detailLocation;
    private String  time;

    public LocationDetailReponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetailLocation() {
        return detailLocation;
    }

    public void setDetailLocation(String detailLocation) {
        this.detailLocation = detailLocation;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LocationDetailReponse(Long id, String detailLocation, String time) {
        this.id = id;
        this.detailLocation = detailLocation;
        this.time = time;
    }
}
