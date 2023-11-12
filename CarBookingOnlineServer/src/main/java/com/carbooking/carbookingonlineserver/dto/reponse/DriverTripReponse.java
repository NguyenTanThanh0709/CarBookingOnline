package com.carbooking.carbookingonlineserver.dto.reponse;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DriverTripReponse {
    private Long id;
    private Date date;
    private Boolean status;

    private Long idtrip;
    private String pickupLocation;
    private String dropoffLocation;
    private LocalTime pickupTime;
    private LocalTime dropoffTime;
    private Integer price;
}
