package com.carbooking.carbookingonlineserver.API.Company.Staff;

import com.carbooking.carbookingonlineserver.dto.reponse.BookingReponse;
import com.carbooking.carbookingonlineserver.entity.Booking;
import com.carbooking.carbookingonlineserver.utils.BookingFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@RestController
@RequestMapping("/api/v1/staff")
@CrossOrigin(origins = "http://localhost:3000")
public class BookingAPIStaff {
    @Autowired
    private BookingFile bookingFile;
    @PostMapping("/booking/list")
    public ResponseEntity<?> uploadListFile(@RequestParam("file") MultipartFile file){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            List<BookingReponse> list = bookingFile.readCsvData(reader);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi nếu cần
        }
        String Message = "Không thể upload file Booking!";
        return new ResponseEntity<>(Message, HttpStatus.BAD_REQUEST);
    }
}
