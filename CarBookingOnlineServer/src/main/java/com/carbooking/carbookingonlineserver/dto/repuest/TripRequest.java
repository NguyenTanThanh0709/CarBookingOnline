package com.carbooking.carbookingonlineserver.dto.repuest;

import com.carbooking.carbookingonlineserver.utils.LocalTimeConverter;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TripRequest {
    @CsvBindByName(column = "id")
    private Long id;
    @CsvBindByName(column = "Điểm đi")
    private String pickupLocation;
    @CsvBindByName(column = "Điểm đến")
    private String dropoffLocation;
    @CsvCustomBindByName(column = "Giờ đi", converter = LocalTimeConverter.class)
    private LocalTime pickupTime;
    @CsvCustomBindByName(column = "Giờ đến", converter = LocalTimeConverter.class)
    private LocalTime  dropoffTime;
    @CsvBindByName(column = "Số giờ chạy")
    private Integer aboutHours;
    @CsvBindByName(column = "Giá")
    private Integer price;
    @CsvBindByName(column = "Số điện thoại công ty")
    private String phoneCompany;
    @CsvBindByName(column = "trạng thái")
    private Boolean status;
    private String urlimage ="https://storage.dx.gov.vn/Data/c_0/2022/10/07/1-638007365766931686.jpg";
}
