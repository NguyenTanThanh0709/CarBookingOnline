package com.carbooking.carbookingonlineserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "PickupLocation", length = 255)
    private String pickupLocation;

    @Column(name = "DropoffLocation", length = 255)
    private String dropoffLocation;

    @Column(name = "PickupTime")
    private LocalTime pickupTime;

    @Column(name = "DropoffTime")
    private LocalTime dropoffTime;

    @Column(name = "abouthours")
    private Integer aboutHours;

    @Column(name = "price")
    private Integer price;

    private Boolean status;
    private String urlimage ="https://storage.dx.gov.vn/Data/c_0/2022/10/07/1-638007365766931686.jpg";

    @ManyToOne
    @JoinColumn(name = "phone_company")
    private Company phoneCompany;

    @OneToMany(mappedBy = "trip")
    private List<DetailPickUpLocation> pickupLocations;

    @OneToMany(mappedBy = "trip")
    private List<DetailDropOffLocation> dropoffLocations;

    @OneToMany(mappedBy = "trip")
    @JsonIgnore
    private List<DriverTrip> driverTrips;

    @ManyToOne
    @JoinColumn(name = "PromotionsID")
    private Promotions promotion;
}
