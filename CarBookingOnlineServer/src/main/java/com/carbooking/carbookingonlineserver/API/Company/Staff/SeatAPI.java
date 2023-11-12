package com.carbooking.carbookingonlineserver.API.Company.Staff;

import com.carbooking.carbookingonlineserver.entity.Car;
import com.carbooking.carbookingonlineserver.entity.Seat;
import com.carbooking.carbookingonlineserver.service.CarService;
import com.carbooking.carbookingonlineserver.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/staff")
@CrossOrigin(origins = "http://localhost:3000")
public class SeatAPI {
    @Autowired
    private SeatService seatService;
    @Autowired
    private CarService carService;

    @GetMapping("seats/{id}")
    public ResponseEntity<?> getSeatById(@PathVariable Long id){
        Seat seat = seatService.getByid(id);
        if(seat != null){
            return new ResponseEntity<>(seat, HttpStatus.OK);
        }
        String Message = "Không tìm thấy chỗ ngồi";
        return new ResponseEntity<>(Message, HttpStatus.NOT_FOUND);
    }

    @GetMapping("seats/car/{id}")
    public ResponseEntity<?> getSeatById(@PathVariable String id){
        Car car = carService.getCarById(id);
        if(car == null){
            String Message = "Không tìm thấy xe";
            return new ResponseEntity<>(Message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(seatService.listSeatOfCar(car), HttpStatus.OK);
    }

}
