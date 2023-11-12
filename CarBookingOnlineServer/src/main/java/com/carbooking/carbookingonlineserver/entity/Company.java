package com.carbooking.carbookingonlineserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Company")
public class Company
{
    @Id
    private String phone;
    private String name;
    @Column(columnDefinition = "TEXT")
    private  String description;
    private Boolean status;
    private String vnp_TmnCode;
    private String vnp_HashSecret;

    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private List<User> users;

    @OneToMany(mappedBy = "phoneCompany")
    @JsonIgnore
    private List<Car> cars;

    @OneToMany(mappedBy = "phoneCompany")
    @JsonIgnore
    private List<Trip> trips;

    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private List<Promotions> promotions;

    private String email;
}
