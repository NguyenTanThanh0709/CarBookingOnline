package com.example.drivercarpaking.models;

public class AuthResponse {

    private String access_token;
    private String refresh_token;
    private String role;

    public AuthResponse() {
    }

    public AuthResponse(String access_token, String refresh_token, String role, String phone, String email) {
        this.access_token = access_token;
        this.refresh_token = refresh_token;
        this.role = role;
        this.phone = phone;
        this.email = email;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    private String phone;
    private String email;
}
