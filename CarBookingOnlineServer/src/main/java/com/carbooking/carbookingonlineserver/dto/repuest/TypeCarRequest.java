package com.carbooking.carbookingonlineserver.dto.repuest;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TypeCarRequest {
    private Long id;
    @CsvBindByName(column = "Tên Loại xe")
    private String name;
    @CsvBindByName(column = "Số chỗ")
    private Integer numberOfSeats;
    @CsvBindByName(column = "Trạng thái")
    private Boolean status;
}
