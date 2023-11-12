package com.carbooking.carbookingonlineserver.dto.repuest;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.Column;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CompanyRepuest {
    @CsvBindByName(column = "SDT_CTY")
    private String phone;
    @CsvBindByName(column = "Tên công ty")
    private String name;
    @CsvBindByName(column = "Mô tả công ty")
    private  String description;
    @CsvBindByName(column = "Trạng thái hoạt động")
    private Boolean status;
    @CsvBindByName(column = "vnp_TmnCode")
    private String vnp_TmnCode;
    @CsvBindByName(column = "vnp_HashSecret")
    private String vnp_HashSecret;
    @CsvBindByName(column = "email")
    private String email;
}
