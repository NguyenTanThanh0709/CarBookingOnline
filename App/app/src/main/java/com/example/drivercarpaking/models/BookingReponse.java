package com.example.drivercarpaking.models;

import java.util.Date;
import java.util.List;

public class BookingReponse {

    private Long id;
    private long fareAmount;
    private String status;
    private Date date;
    private String description;
    private Boolean isProtect;



    private LocationDetailReponse locationDetailPickUp;
    private LocationDetailReponse locationDetailDropOff;
    private UserReponse user;
    private List<SeatReponse> seats;
    private CarReponse car;
    private DriverTripReponse drivertrip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getFareAmount() {
        return fareAmount;
    }

    public void setFareAmount(long fareAmount) {
        this.fareAmount = fareAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getProtect() {
        return isProtect;
    }

    public void setProtect(Boolean protect) {
        isProtect = protect;
    }

    public LocationDetailReponse getLocationDetailPickUp() {
        return locationDetailPickUp;
    }

    public void setLocationDetailPickUp(LocationDetailReponse locationDetailPickUp) {
        this.locationDetailPickUp = locationDetailPickUp;
    }

    public LocationDetailReponse getLocationDetailDropOff() {
        return locationDetailDropOff;
    }

    public void setLocationDetailDropOff(LocationDetailReponse locationDetailDropOff) {
        this.locationDetailDropOff = locationDetailDropOff;
    }

    public UserReponse getUser() {
        return user;
    }

    public void setUser(UserReponse user) {
        this.user = user;
    }

    public List<SeatReponse> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatReponse> seats) {
        this.seats = seats;
    }

    public CarReponse getCar() {
        return car;
    }

    public void setCar(CarReponse car) {
        this.car = car;
    }

    public DriverTripReponse getDrivertrip() {
        return drivertrip;
    }

    public void setDrivertrip(DriverTripReponse drivertrip) {
        this.drivertrip = drivertrip;
    }

    public BookingReponse() {
    }

    public BookingReponse(Long id, long fareAmount, String status, Date date, String description, Boolean isProtect, LocationDetailReponse locationDetailPickUp, LocationDetailReponse locationDetailDropOff, UserReponse user, List<SeatReponse> seats, CarReponse car, DriverTripReponse drivertrip) {
        this.id = id;
        this.fareAmount = fareAmount;
        this.status = status;
        this.date = date;
        this.description = description;
        this.isProtect = isProtect;
        this.locationDetailPickUp = locationDetailPickUp;
        this.locationDetailDropOff = locationDetailDropOff;
        this.user = user;
        this.seats = seats;
        this.car = car;
        this.drivertrip = drivertrip;
    }
}
