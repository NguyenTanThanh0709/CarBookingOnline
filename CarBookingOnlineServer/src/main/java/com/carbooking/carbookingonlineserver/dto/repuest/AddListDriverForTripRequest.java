package com.carbooking.carbookingonlineserver.dto.repuest;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddListDriverForTripRequest {
    private Long idtrip;
    private List<String> phoneDrivers;
}
