package com.carbooking.carbookingonlineserver.dto.repuest;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ListDriverTripDTO {

    private String phoneCompany;
    private Long idTrip;
    private String start;
    private String end;
    private Long typecar;
}
