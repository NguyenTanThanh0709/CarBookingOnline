package com.carbooking.carbookingonlineserver.dto.reponse;

import jakarta.persistence.Column;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SeatReponse {
    private Long id;
    private String name;
}
