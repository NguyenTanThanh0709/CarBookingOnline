package com.carbooking.carbookingonlineserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_driver_trip")
public class UserDriverTrip {
    @EmbeddedId
    @JsonIgnore
    private UserDriverTripId id;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("driverTripId")
    @JoinColumn(name = "driver_trip_id")
    @JsonIgnore
    private DriverTrip driverTrip;

    @JsonIgnore
    private Boolean status;
    @JsonIgnore
    private Date date;
}
