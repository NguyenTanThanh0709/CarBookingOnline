package com.carbooking.carbookingonlineserver.dto.reponse;

import com.carbooking.carbookingonlineserver.entity.Company;
import com.carbooking.carbookingonlineserver.entity.TypeCar;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CarReponse {
    private String licenseplates;
    private Boolean availability;
    private Long idTypeCar;
    private String name;
    private Integer numberOfSeats;
}
