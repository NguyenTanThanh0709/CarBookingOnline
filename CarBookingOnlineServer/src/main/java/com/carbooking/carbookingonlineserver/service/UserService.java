package com.carbooking.carbookingonlineserver.service;

import com.carbooking.carbookingonlineserver.dto.Role;
import com.carbooking.carbookingonlineserver.dto.repuest.EmployeeRequest;
import com.carbooking.carbookingonlineserver.entity.DriverTrip;
import com.carbooking.carbookingonlineserver.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Boolean existsByEmail(String email);
    Boolean existsByPhone(String phone);
    Boolean exitsByLicenseNumberExists(String LicenseNumberExists);
    User findByPhone(String phone);
    User add(EmployeeRequest employeeRequest);
    User editEmployee(String phone, EmployeeRequest employeeRequest);
    User deleteEmployee(String phone);
    List<User> getAllEmployeeByCompanyAndRole(Role role, String phoneCompany);
    List<User> getAllEmployeeByCompany(String phoneCompany);

    Boolean updateStatusDriver(String phone, Boolean status);

    List<User> addList(List<EmployeeRequest> list);
}
