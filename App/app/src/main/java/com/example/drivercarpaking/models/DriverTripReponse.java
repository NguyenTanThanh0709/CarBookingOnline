package com.example.drivercarpaking.models;

import java.time.LocalTime;
import java.util.Date;

public class DriverTripReponse {
    private Long id;
    private Date date;
    private Boolean status;

    private Long idtrip;
    private String pickupLocation;
    private String dropoffLocation;
    private String pickupTime;

    public DriverTripReponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getIdtrip() {
        return idtrip;
    }

    public void setIdtrip(Long idtrip) {
        this.idtrip = idtrip;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(String dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getDropoffTime() {
        return dropoffTime;
    }

    public void setDropoffTime(String dropoffTime) {
        this.dropoffTime = dropoffTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public DriverTripReponse(Long id, Date date, Boolean status, Long idtrip, String pickupLocation, String dropoffLocation, String pickupTime, String dropoffTime, Integer price) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.idtrip = idtrip;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.pickupTime = pickupTime;
        this.dropoffTime = dropoffTime;
        this.price = price;
    }

    private String dropoffTime;
    private Integer price;
}
