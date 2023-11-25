package com.example.drivercarpaking.models;

import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

public class DriverTripReponDriver {
    private Long id;
    private Date date;
    private Boolean status;


    private String pickupLocation;
    private String dropoffLocation;
    private String pickupTime;
    private String dropoffTime;


    private String licenseplates;
    private  String nameCar;

    private Set<Seat> seats;

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

    public String getLicenseplates() {
        return licenseplates;
    }

    public void setLicenseplates(String licenseplates) {
        this.licenseplates = licenseplates;
    }

    public String getNameCar() {
        return nameCar;
    }

    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    public DriverTripReponDriver() {
    }

    public DriverTripReponDriver(Long id, Date date, Boolean status, String pickupLocation, String dropoffLocation, String pickupTime, String dropoffTime, String licenseplates, String nameCar, Set<Seat> seats) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.pickupTime = pickupTime;
        this.dropoffTime = dropoffTime;
        this.licenseplates = licenseplates;
        this.nameCar = nameCar;
        this.seats = seats;
    }
}
