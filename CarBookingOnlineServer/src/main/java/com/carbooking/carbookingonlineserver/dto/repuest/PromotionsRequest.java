package com.carbooking.carbookingonlineserver.dto.repuest;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PromotionsRequest {
    private Long id;
    private String code;
    private String description;
    private Boolean status;
    private BigDecimal discountAmount;
    private String phoneCompany;
}
