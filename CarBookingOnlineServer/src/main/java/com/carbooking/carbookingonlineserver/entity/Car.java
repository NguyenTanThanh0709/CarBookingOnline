package com.carbooking.carbookingonlineserver.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Car",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "licenseplates")
})
public class Car {
    @Id
    @Column( length = 255)
    private String id;

    @Column( length = 255)
    private String licenseplates;

    @Column(name = "Availability")
    private Boolean availability;

    @ManyToOne
    @JoinColumn(name = "phone_company")
    private Company phoneCompany;

    @ManyToOne
    @JoinColumn(name = "idtypecar")
    private TypeCar typeCar;

    @OneToMany(mappedBy = "car")
    private List<Seat> seats;

    @OneToMany(mappedBy = "car")
    @JsonIgnore
    private List<DriverTrip> driverTrips;

    @OneToMany(mappedBy = "car")
    @JsonIgnore
    private List<Booking> bookings;
}
