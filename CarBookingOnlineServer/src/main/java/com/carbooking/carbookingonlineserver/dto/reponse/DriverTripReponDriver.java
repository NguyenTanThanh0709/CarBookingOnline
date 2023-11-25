package com.carbooking.carbookingonlineserver.dto.reponse;

import com.carbooking.carbookingonlineserver.entity.Car;
import com.carbooking.carbookingonlineserver.entity.Seat;
import com.carbooking.carbookingonlineserver.entity.Trip;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DriverTripReponDriver {
    private Long id;
    private Date date;
    private Boolean status;


    private String pickupLocation;
    private String dropoffLocation;
    private LocalTime pickupTime;
    private LocalTime dropoffTime;


    private String licenseplates;
    private  String nameCar;

    private Set<Seat> seats;


}
