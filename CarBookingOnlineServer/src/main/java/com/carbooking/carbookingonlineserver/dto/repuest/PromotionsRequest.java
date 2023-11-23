package com.carbooking.carbookingonlineserver.dto.repuest;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PromotionsRequest {
    private Long id;
    private String code;
    private String description;
    private BigDecimal discountAmount;
    private String trips;
    private String company;
    private Date startDate;
    private Date endDate;
}
