package com.carbooking.carbookingonlineserver.dto.repuest;

import com.carbooking.carbookingonlineserver.dto.Role;
import com.opencsv.bean.CsvBindByName;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeRequest {
    @CsvBindByName(column = "Tên nhân viên")
    private String name;
    @CsvBindByName(column = "Số điện thoại")
    private String phone;
    @CsvBindByName(column = "Email")
    private String email;
    @CsvBindByName(column = "Mật khẩu")
    private String password;
    @CsvBindByName(column = "Role")
    private Role role;
    @CsvBindByName(column = "Trạng thái")
    private Boolean status;
    @CsvBindByName(column = "Bằng lái xe (tài xế)")
    private String licenseNumber;
    @CsvBindByName(column = "Số điện thoại công ty")
    private String phoneCompany;
}
