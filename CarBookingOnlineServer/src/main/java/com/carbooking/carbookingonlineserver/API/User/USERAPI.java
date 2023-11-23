package com.carbooking.carbookingonlineserver.API.User;

import com.carbooking.carbookingonlineserver.entity.User;
import com.carbooking.carbookingonlineserver.repository.UserRepository;
import com.carbooking.carbookingonlineserver.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/demo")
@CrossOrigin(origins = "http://localhost:3000")
public class USERAPI {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private IMailService mailService;

    @GetMapping("get/{phone}")
    public ResponseEntity<?> getone(
            @PathVariable String phone) {
        try {
            userRepository.findByPhone(phone);

            return ResponseEntity.ok(userRepository.findByPhone(phone).get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating user: " + e.getMessage());
        }
    }
    @PostMapping("/forgot")
    public ResponseEntity<?> forgot(@RequestParam String email){

        try {
            Optional<User> optionalUser = userRepository.findByEmail(email);
            if(optionalUser.isPresent()){
                userRepository.updatePasswordByPhone(passwordEncoder.encode("swq21x121a"), optionalUser.get().getPhone());
                mailService.sendMailPassword("swq21x121a",email);
                return ResponseEntity.ok("Khôi phục Thành Công Vui Lòng Kiểm Tra Mail!");
            }
            return ResponseEntity.ok("Email Không Tồn Tại");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating user: " + e.getMessage());
        }
    }

    @PutMapping("update/{phone}")
    public ResponseEntity<String> updateNameAndEmail(
            @PathVariable String phone,
            @RequestParam String name,
            @RequestParam String email) {
        try {
            userRepository.updateNameAndEmailByPhone(name, email, phone);
            return ResponseEntity.ok("User updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating user: " + e.getMessage());
        }
    }

    @PutMapping("/password/{phone}")
    public ResponseEntity<String> updatepassword(
            @PathVariable String phone,
            @RequestParam String passwordnew,
            @RequestParam String passwordold) {
        try {
            Optional<User> optionalUser = userRepository.findByPhone(phone);
            if(optionalUser.isPresent() ){
                if(passwordEncoder.matches(passwordold, optionalUser.get().getPassword())){
                    userRepository.updatePasswordByPhone(passwordEncoder.encode(passwordnew), phone);
                    return ResponseEntity.ok("User updated successfully.");

                }else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("Mật khẩu cũ không trùng khớp");
                }
            }else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Không tìm thấy user");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating user: " + e.getMessage());
        }
    }
}
