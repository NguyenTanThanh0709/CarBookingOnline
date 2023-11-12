package com.carbooking.carbookingonlineserver.auth;

import com.carbooking.carbookingonlineserver.dto.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("role")
    private Role role;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("email")
    private String email;
}