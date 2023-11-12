package com.carbooking.carbookingonlineserver.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "DriverTrip")
public class DriverTrip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "id_trip")
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToMany(mappedBy = "driverTrip")
    @JsonIgnore
    private List<Booking> bookings;

    @OneToMany(mappedBy = "driverTrip")
    private List<UserDriverTrip> userDriverTrips;

    @ManyToMany
    @JoinTable(name = "driver_trip_seat",
            joinColumns = @JoinColumn(name = "driver_trip_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id"))
    private Set<Seat> seats;
}
