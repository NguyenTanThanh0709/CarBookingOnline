package com.example.drivercarpaking.models;
public class UserReponse {
    private String phone;
    private String email;
    private String password;
    private String name;
    private Integer points;

    public UserReponse() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public UserReponse(String phone, String email, String password, String name, Integer points) {
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.name = name;
        this.points = points;
    }
}
