package com.carbooking.carbookingonlineserver.dto.repuest;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CarRequest {
    @CsvBindByName(column = "Mã")
    private String id;
    @CsvBindByName(column = "Biển số xe")
    private String licenseplates;
    @CsvBindByName(column = "Tình trạng")
    private Boolean availability;
    @CsvBindByName(column = "Số điện thoại công ty")
    private String phoneCompanyId;
    @CsvBindByName(column = "Loại xe")
    private Long typeCarId;
}
