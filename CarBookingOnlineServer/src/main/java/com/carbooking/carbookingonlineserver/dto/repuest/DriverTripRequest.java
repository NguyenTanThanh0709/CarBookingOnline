package com.carbooking.carbookingonlineserver.dto.repuest;


import com.carbooking.carbookingonlineserver.entity.Booking;
import com.carbooking.carbookingonlineserver.entity.Car;
import com.carbooking.carbookingonlineserver.entity.Trip;
import com.carbooking.carbookingonlineserver.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DriverTripRequest {
    @CsvBindByName(column = "id")
    private Long id;

    @CsvBindByName(column = "Ngày đi")
    @CsvDate("MM/dd/yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy", timezone = "UTC")
    private Date date;

    @CsvBindByName(column = "Trạng thái")
    private Boolean status;

    @CsvBindByName(column = "Mã tuyến")
    private Long  idtrip;

    @CsvBindByName(column = "Biển số xe")
    private String idcar;

    @CsvBindByName(column = "Danh sách tài xế")
    private String drivers;
}
