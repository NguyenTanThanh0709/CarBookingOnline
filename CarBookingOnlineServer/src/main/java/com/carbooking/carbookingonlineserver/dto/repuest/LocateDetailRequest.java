package com.carbooking.carbookingonlineserver.dto.repuest;
import com.carbooking.carbookingonlineserver.utils.LocalTimeConverter;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import lombok.*;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LocateDetailRequest {
    @CsvBindByName(column = "id")
    private Long id;
    @CsvBindByName(column = "Chi tiết các điểm đón và trả")
    private String detailLocation;
    @CsvCustomBindByName(column = "Giờ", converter = LocalTimeConverter.class)
    private LocalTime time;
    @CsvBindByName(column = "Thuộc tuyến nào(mã)")
    private Long idtrip;
}
