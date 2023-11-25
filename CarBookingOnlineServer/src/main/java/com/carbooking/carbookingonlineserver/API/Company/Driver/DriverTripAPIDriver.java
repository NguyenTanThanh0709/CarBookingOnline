package com.carbooking.carbookingonlineserver.API.Company.Driver;

import com.carbooking.carbookingonlineserver.dto.reponse.DriverTripReponDriver;
import com.carbooking.carbookingonlineserver.entity.DriverTrip;
import com.carbooking.carbookingonlineserver.entity.User;
import com.carbooking.carbookingonlineserver.entity.UserDriverTrip;
import com.carbooking.carbookingonlineserver.service.implservice.UserDriverTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/driver")
@CrossOrigin(origins = "http://localhost:3000")
public class DriverTripAPIDriver {

    @Autowired
    private UserDriverTripService userDriverTripService;

    @GetMapping("/listdriver/{phone}")
    public ResponseEntity<List<DriverTripReponDriver>> getUserDriverTripsByDriverTrip(@PathVariable String phone) {
        List<DriverTripReponDriver> list = userDriverTripService.getDriverTripsByUserPhone(phone);
        return ResponseEntity.ok(list);
    }



}
