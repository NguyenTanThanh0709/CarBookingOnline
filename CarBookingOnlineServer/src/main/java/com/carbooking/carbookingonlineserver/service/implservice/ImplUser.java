package com.carbooking.carbookingonlineserver.service.implservice;


import com.carbooking.carbookingonlineserver.dto.Role;
import com.carbooking.carbookingonlineserver.dto.repuest.EmployeeRequest;
import com.carbooking.carbookingonlineserver.entity.Company;
import com.carbooking.carbookingonlineserver.entity.DriverTrip;
import com.carbooking.carbookingonlineserver.entity.User;
import com.carbooking.carbookingonlineserver.repository.UserRepository;
import com.carbooking.carbookingonlineserver.service.CompanyService;
import com.carbooking.carbookingonlineserver.service.DriverTripService;
import com.carbooking.carbookingonlineserver.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ImplUser implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private  ModelMapper mapper;

    @Autowired
    private DriverTripService driverTripService;

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Boolean existsByPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }

    @Override
    public Boolean exitsByLicenseNumberExists(String LicenseNumberExists) {
        return userRepository.findByLicenseNumber(LicenseNumberExists).isPresent();
    }

    @Override
    public User findByPhone(String phone) {
        Optional<User> userOptional = userRepository.findByPhone(phone);
        if(userOptional.isPresent()){
            return  userOptional.get();
        }
        return null;
    }

    @Override
    public User add(EmployeeRequest employeeRequest) {
        User employee = mapper.map(employeeRequest,User.class);
        Company company = companyService.getCompany(employeeRequest.getPhoneCompany());
        if(company != null){
            employee.setCompany(company);
        }
        return userRepository.save(employee);
    }

    @Override
    public User editEmployee(String phone, EmployeeRequest employeeRequest) {
        User existingUser = userRepository.findByPhone(phone).get();

        // Kiểm tra xem người dùng cần chỉnh sửa có tồn tại hay không
        if (existingUser == null) {
            // Người dùng không tồn tại, bạn có thể xử lý lỗi ở đây hoặc thông báo cho người dùng
            return null;
        }
        mapper.map(employeeRequest,existingUser);
        return userRepository.save(existingUser);
    }

    @Override
    public User deleteEmployee(String phone) {
        User user = findByPhone(phone);
        if(user == null){
            return null;
        }
        user.setStatus(false);
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getAllEmployeeByCompanyAndRole(Role role, String phoneCompany) {
        List<User> list = new ArrayList<>();
        Company company = companyService.getCompany(phoneCompany);
        if(company != null){
            list = userRepository.findByRoleAndCompany(role,company);
        }
        return list;
    }

    @Override
    public List<User> getAllEmployeeByCompany(String phoneCompany) {
        List<User> list = new ArrayList<>();
        Company company = companyService.getCompany(phoneCompany);
        if(company != null){
            list = userRepository.findByCompany(company);
        }
        return list;
    }
    //=====================================

    private Boolean checkDriver(User user){
        if(user != null){
            if(user.getStatus() == false && user.getRole().equals(Role.DRIVER) && user.getLicenseNumber() != null){
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean updateStatusDriver(String phone, Boolean status) {
        userRepository.updateStatusForDriversByPhone(status,phone);
        return status;
    }

    @Override
    public List<User> addList(List<EmployeeRequest> list_) {
        List<User> list  = new ArrayList<>();
        for(int i = 0; i < list_.size(); i++){
            User car = mapper.map(list_.get(i), User.class);
            list.add(car);
        }
        List<User> list__ = userRepository.saveAll(list);
        return list__;
    }
}
