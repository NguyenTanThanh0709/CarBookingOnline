package com.carbooking.carbookingonlineserver.API;

import com.carbooking.carbookingonlineserver.dto.repuest.LocateDetailRequest;
import com.carbooking.carbookingonlineserver.service.implservice.MailService;
import com.carbooking.carbookingonlineserver.utils.CloudinaryService;
import com.carbooking.carbookingonlineserver.utils.DetailLocateFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/demo")
@CrossOrigin(origins = "http://localhost:3000")
public class demo {



    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("heello mek21w");
    }



}
