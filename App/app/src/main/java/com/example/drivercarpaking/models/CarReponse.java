package com.example.drivercarpaking.models;
public class CarReponse {
    private String licenseplates;
    private Boolean availability;
    private Long idTypeCar;
    private String name;
    private Integer numberOfSeats;

    public CarReponse() {
    }

    public String getLicenseplates() {
        return licenseplates;
    }

    public void setLicenseplates(String licenseplates) {
        this.licenseplates = licenseplates;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public Long getIdTypeCar() {
        return idTypeCar;
    }

    public void setIdTypeCar(Long idTypeCar) {
        this.idTypeCar = idTypeCar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public CarReponse(String licenseplates, Boolean availability, Long idTypeCar, String name, Integer numberOfSeats) {
        this.licenseplates = licenseplates;
        this.availability = availability;
        this.idTypeCar = idTypeCar;
        this.name = name;
        this.numberOfSeats = numberOfSeats;
    }
}
