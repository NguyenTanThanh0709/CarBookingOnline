package com.carbooking.carbookingonlineserver.API.Admin;

import com.carbooking.carbookingonlineserver.dto.repuest.TypeCarRequest;
import com.carbooking.carbookingonlineserver.entity.Company;
import com.carbooking.carbookingonlineserver.service.TypeCarService;
import com.carbooking.carbookingonlineserver.utils.TypeCarFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.carbooking.carbookingonlineserver.entity.TypeCar;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@RestController
@RequestMapping("/api/v2/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class TypeCarAPI {
    @Autowired
    private TypeCarService typeCarService;

    @Autowired
    private TypeCarFile typeCarFile;


    @GetMapping("/typecar")
    public ResponseEntity<List<TypeCar>> getAllTypeCars() {
        List<TypeCar> list = typeCarService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/typecar/{id}")
    public ResponseEntity<TypeCar> getTypeCar(@PathVariable Long id) {
        TypeCar typeCar = typeCarService.GetTypecar(id);
        if (typeCar != null) {
            return new ResponseEntity<>(typeCar, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/typecar")
    public ResponseEntity<TypeCar> createTypeCar(@RequestBody TypeCarRequest typeCar) {
        TypeCar createdTypeCar = typeCarService.addTypecar(typeCar);
        return new ResponseEntity<>(createdTypeCar, HttpStatus.CREATED);
    }

    @PutMapping("/typecar/{id}")
    public ResponseEntity<TypeCar> updateTypeCar(@PathVariable Long id, @RequestBody TypeCarRequest typeCar) {
        TypeCar updatedTypeCar = typeCarService.EditTypecar(id, typeCar);
        if (updatedTypeCar != null) {
            return new ResponseEntity<>(updatedTypeCar, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/typecar/{id}")
    public ResponseEntity<?> deleteTypeCar(@PathVariable Long id) {
        TypeCar typeCar = typeCarService.deleteTypecar(id);
        return new ResponseEntity<>(typeCar,HttpStatus.OK);
    }

    @PostMapping("/typecar/list")
    public ResponseEntity<?> uploadListFile(@RequestParam("file") MultipartFile file){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            List<TypeCar> list = typeCarFile.readCsvData(reader);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi nếu cần
        }
        String Message = "Không thể upload file typecar!";
        return new ResponseEntity<>(Message, HttpStatus.BAD_REQUEST);
    }
}
