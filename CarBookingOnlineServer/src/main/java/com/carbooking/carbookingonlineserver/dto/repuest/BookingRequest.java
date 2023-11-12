package com.carbooking.carbookingonlineserver.dto.repuest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookingRequest {
    @CsvBindByName(column = "id")
    private Long id;
    @CsvBindByName(column = "giá tiền")
    private BigDecimal fareAmount;
    @CsvBindByName(column = "trạng thái")
    private String status;
    @CsvBindByName(column = "date")
    @CsvDate("MM/dd/yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy", timezone = "UTC")
    private Date date;
    @CsvBindByName(column = "mô tả")
    private String description;
    @CsvBindByName(column = "isProtect")
    private Boolean isProtect;


    @CsvBindByName(column = "mã chuyến đi")
    private Long iddriverTrip;
    @CsvBindByName(column = "điểm đón chi tiết")
    private Long idPickUp;
    @CsvBindByName(column = "điểm đến chi tiết")
    private Long idDropOff;
    @CsvBindByName(column = "danh sách chỗ ngồi")
    private String listIdSeat; // theo quy tắc id-id-id
    @CsvBindByName(column = "biển số xe")
    private String idCar;



    @CsvBindByName(column = "Số điện thoại KH")
    private String phoneUser;
}
