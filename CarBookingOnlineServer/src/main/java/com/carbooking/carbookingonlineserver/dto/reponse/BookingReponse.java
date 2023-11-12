package com.carbooking.carbookingonlineserver.dto.reponse;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookingReponse {

    private Long id;
    private long fareAmount;
    private String status;
    private Date date;
    private String description;
    private Boolean isProtect;



    private LocationDetailReponse locationDetailPickUp;
    private LocationDetailReponse locationDetailDropOff;
    private UserReponse user;
    private List<SeatReponse> seats;
    private CarReponse car;
    private DriverTripReponse drivertrip;


}
