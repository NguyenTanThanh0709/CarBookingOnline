package com.carbooking.carbookingonlineserver.API.User;

import com.carbooking.carbookingonlineserver.entity.DriverTrip;
import com.carbooking.carbookingonlineserver.repository.DriverTripRepository;
import com.carbooking.carbookingonlineserver.service.DriverTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:3000")
public class DriverTripAPIU {
    @Autowired
    private DriverTripService driverTripService;

    @Autowired
    private DriverTripRepository driverTripRepository;

    @GetMapping("/drivertrip/listbydate")
    public ResponseEntity<?> getlistbydate(
            @RequestParam String locateend,
            @RequestParam String locatestart,
            @RequestParam @DateTimeFormat(pattern = "MM/dd/yyy") Date date
    ){
        List<DriverTrip> list = new ArrayList<>();
        list = driverTripRepository.findByDate(date);
        if(list != null && list.size() >0){
            List<DriverTrip> listTEMP = new ArrayList<>();
            for (DriverTrip driverTrip : list){
                if(driverTrip.getTrip().getPickupLocation().toLowerCase().equals(locatestart.toLowerCase())
                    && driverTrip.getTrip().getDropoffLocation().toLowerCase().equals(locateend.toLowerCase()
                )){
                    listTEMP.add(driverTrip);
                }
            }
            return new ResponseEntity<>(listTEMP, HttpStatus.OK);
        }
        String message = "Không thể tìm được chuyến đi của ngày: " + date.toString() ;
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
