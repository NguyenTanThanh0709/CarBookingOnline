package com.carbooking.carbookingonlineserver.API.Company.Owner;

import com.carbooking.carbookingonlineserver.dto.repuest.CarRequest;
import com.carbooking.carbookingonlineserver.dto.repuest.TypeCarRequest;
import com.carbooking.carbookingonlineserver.entity.Car;
import com.carbooking.carbookingonlineserver.entity.Trip;
import com.carbooking.carbookingonlineserver.entity.TypeCar;
import com.carbooking.carbookingonlineserver.service.CarService;
import com.carbooking.carbookingonlineserver.service.TypeCarService;
import com.carbooking.carbookingonlineserver.utils.CarFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@RestController
@RequestMapping("/api/v1/owner")
@CrossOrigin(origins = "http://localhost:3000")
public class CarManagementAPI {
    @Autowired
    private CarService carService;
    @Autowired
    private TypeCarService typeCarService;
    @Autowired
    private CarFile carFile;

    @GetMapping("/cars/company/{phonecompany}")
    public ResponseEntity<List<Car>> getAllTypeCarsBycompany(@PathVariable String phonecompany) {
        List<Car> list = carService.getAllByCompany(phonecompany);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getTypeCar(@PathVariable String id) {
        Car car = carService.getCarById(id);
        if (car != null) {
            return new ResponseEntity<>(car, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/cars")
    public ResponseEntity<?> createTypeCar(@RequestBody CarRequest carRequest) {
        // Check if the requested TypeCar exists
        Boolean typeCar = typeCarService.checkExist(carRequest.getTypeCarId());
        if (typeCar == false) {
            return new ResponseEntity<>("TypeCar does not exist", HttpStatus.BAD_REQUEST);
        }
        // Check if the requested Car ID already exists
        if (carService.checkExistLince(carRequest.getLicenseplates())) {
            return new ResponseEntity<>("Car with this ID already exists", HttpStatus.CONFLICT);
        }
        carRequest.setId(carRequest.getLicenseplates());
        Car car = carService.add(carRequest);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> updateTypeCar(@PathVariable String id, @RequestBody CarRequest carRequest) {
        carRequest.setId(id);
        Car updatedTypeCar = carService.edit(id, carRequest);
        if (updatedTypeCar != null) {
            return new ResponseEntity<>(updatedTypeCar, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/cars/{id}/availability")
    public ResponseEntity<?> editIsAvailable(@RequestParam Boolean status,@PathVariable String id) {
        Car car = carService.getCarById(id);
        if (car != null) {
            carService.setAvailble(status,id);
            return new ResponseEntity<>("Done",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/cars/list")
    public ResponseEntity<?> uploadListFile(@RequestParam("file") MultipartFile file){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            List<Car> list = carFile.readCsvData(reader);
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
