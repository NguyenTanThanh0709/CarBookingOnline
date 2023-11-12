package com.carbooking.carbookingonlineserver.API.Admin;

import com.carbooking.carbookingonlineserver.auth.RegisterRequest;
import com.carbooking.carbookingonlineserver.dto.repuest.EmployeeRequest;
import com.carbooking.carbookingonlineserver.entity.Company;
import com.carbooking.carbookingonlineserver.entity.User;
import com.carbooking.carbookingonlineserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class OwnerCompany {
    @Autowired
    private UserService userService;

    @GetMapping("/owner/{phonecompany}")
    private ResponseEntity<?> get(@PathVariable String phonecompany){
         User user = userService.findByPhone(phonecompany);
         if(user != null){
             return new ResponseEntity<>(user, HttpStatus.OK);
         }
        String errorMessage = "Không tìm thấy chủ doanh nghệp";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
