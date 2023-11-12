package com.carbooking.carbookingonlineserver.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class UserDriverTripId implements Serializable {
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "driver_trip_id")
    private Long driverTripId;

}