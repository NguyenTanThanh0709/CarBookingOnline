package com.carbooking.carbookingonlineserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@EqualsAndHashCode
@Table(name = "Seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 20)
    private String name;

    @ManyToOne
    @JoinColumn(name = "idcar")
    @JsonIgnore
    private Car car;

    @ManyToMany(mappedBy = "seats")
    @JsonIgnore
    private Set<Booking> bookings;

    @ManyToMany(mappedBy = "seats")
    @JsonIgnore
    private Set<DriverTrip> driverTrips;



}
