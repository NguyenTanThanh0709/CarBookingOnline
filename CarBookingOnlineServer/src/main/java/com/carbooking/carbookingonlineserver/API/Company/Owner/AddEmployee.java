package com.carbooking.carbookingonlineserver.API.Company.Owner;

import com.carbooking.carbookingonlineserver.dto.Role;
import com.carbooking.carbookingonlineserver.dto.repuest.EmployeeRequest;
import com.carbooking.carbookingonlineserver.entity.Car;
import com.carbooking.carbookingonlineserver.entity.User;
import com.carbooking.carbookingonlineserver.service.UserService;
import com.carbooking.carbookingonlineserver.utils.EmployeeFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/owner")
@CrossOrigin(origins = "http://localhost:3000")
public class AddEmployee {
    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeFile employeeFile;

    @PostMapping("/employees")
    private ResponseEntity<?> addEmployee(@RequestBody EmployeeRequest employeeRequest){
        Boolean existingUSER = userService.existsByPhone(employeeRequest.getPhone());
        Boolean existingUSER_ = userService.existsByEmail(employeeRequest.getEmail());

        if (existingUSER == true || existingUSER_ == true) {
                String errorMessage = "USER với số điện thoại hoặc Email hoặc Role này đã tồn tại! Vui lòng kiểm tra lại.";
                return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        if(employeeRequest.getRole().toString().equals("DRIVER") && (employeeRequest.getLicenseNumber() ==null ||employeeRequest.getLicenseNumber() =="")){


            String errorMessage = "DRIVER thiếu bằng lái xe Vui lòng kiểm tra lại.";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        if(employeeRequest.getRole().toString().equals("DRIVER") && userService.exitsByLicenseNumberExists(employeeRequest.getLicenseNumber())){
            String errorMessage_ = "DRIVER có bằng lái xe đã tồn tại";
            return new ResponseEntity<>(errorMessage_, HttpStatus.BAD_REQUEST);
        }

        employeeRequest.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
        User newUser = userService.add(employeeRequest);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @GetMapping("/employees/{phone}")
    public ResponseEntity<?> getEmployeeByPhone(@PathVariable String phone){
        User user= userService.findByPhone(phone);
        if(user == null){
            String errorMessage = "Không Tồn Tại Nhân Viên Này";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/employees/company/{phoneCompany}")
    public ResponseEntity<List<User>> getAllEmployeeByCompany(@PathVariable String phoneCompany){
        List<User> list = new ArrayList<>();
        list = userService.getAllEmployeeByCompany(phoneCompany);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @DeleteMapping("/employees/{phone}")
    public ResponseEntity<User> deleteEmployee(@PathVariable String phone){
        User company = userService.deleteEmployee(phone);
        if(company != null){
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/employees/{phone}")
    public  ResponseEntity<User> editEmployee(@PathVariable String phone, @RequestBody EmployeeRequest employeeRequest){
        User user = userService.findByPhone(phone);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            employeeRequest.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
            User updated = userService.editEmployee(phone, employeeRequest);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
    }

    @PostMapping("/employees/list")
    public ResponseEntity<?> uploadListFile(@RequestParam("file") MultipartFile file){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            List<User> list = employeeFile.readCsvData(reader);
            if(list == null){
                String Message = "Không thể upload fileCar! Vui Lòng Kiểm Tra Lại File";
                return new ResponseEntity<>(Message, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi nếu cần
        }
        String Message = "Không thể upload fileCar!";
        return new ResponseEntity<>(Message, HttpStatus.BAD_REQUEST);
    }

}
