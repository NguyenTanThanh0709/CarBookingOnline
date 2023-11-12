package com.carbooking.carbookingonlineserver.dto.reponse;

import lombok.*;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LocationDetailReponse {
    private Long id;
    private String detailLocation;
    private LocalTime time;

}
