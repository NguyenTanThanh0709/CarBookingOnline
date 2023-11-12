package com.carbooking.carbookingonlineserver.dto.reponse;

import com.carbooking.carbookingonlineserver.dto.Role;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserReponse {
    private String phone;
    private String email;
    private String password;
    private String name;
    private Integer points;
}
