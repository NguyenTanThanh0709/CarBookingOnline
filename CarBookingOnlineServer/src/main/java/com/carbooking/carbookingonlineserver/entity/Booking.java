package com.carbooking.carbookingonlineserver.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "FareAmount")
    private long fareAmount;

    @Column(name = "Status", length = 50)
    private String status; // chưa thanh toán - đã thanh toán - đã hủy

    @Column(name = "date")
    private Date date;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;

    private Boolean isProtect;

    @ManyToOne
    @JoinColumn(name = "id_pickup")
    @JsonIgnore
    private DetailPickUpLocation pickupLocation;

    @ManyToOne
    @JoinColumn(name = "id_dropoff")
    @JsonIgnore
    private DetailDropOffLocation dropoffLocation;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonIgnore

    private User user;

    @ManyToOne
    @JoinColumn(name = "id_drivertrip")
    @JsonIgnore
    private DriverTrip driverTrip;

    @ManyToMany
    @JoinTable(
            name = "booking_seat",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id")
    )
    @JsonIgnore
    private Set<Seat> seats;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    @JsonIgnore
    private Car car;

    @OneToOne(mappedBy = "booking")
    private Payment payment;

}
