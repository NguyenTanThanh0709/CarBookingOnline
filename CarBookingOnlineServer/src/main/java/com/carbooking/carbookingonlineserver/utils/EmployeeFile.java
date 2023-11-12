package com.carbooking.carbookingonlineserver.utils;

import com.carbooking.carbookingonlineserver.dto.repuest.DriverTripRequest;
import com.carbooking.carbookingonlineserver.dto.repuest.EmployeeRequest;
import com.carbooking.carbookingonlineserver.entity.DriverTrip;
import com.carbooking.carbookingonlineserver.entity.User;
import com.carbooking.carbookingonlineserver.service.DriverTripService;
import com.carbooking.carbookingonlineserver.service.UserService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Service
public class EmployeeFile {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    public List<User> readCsvData(Reader reader) throws IOException {
        CsvToBean<EmployeeRequest> csvToBean = new CsvToBeanBuilder<EmployeeRequest>(reader)
                .withType(EmployeeRequest.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        List<EmployeeRequest> list = csvToBean.parse();
        for (EmployeeRequest employeeRequest: list){
            employeeRequest.setPhone("0"+employeeRequest.getPhone());
            employeeRequest.setPhoneCompany("0"+employeeRequest.getPhoneCompany());
            employeeRequest.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
        }
        return userService.addList(list);
    }
}
