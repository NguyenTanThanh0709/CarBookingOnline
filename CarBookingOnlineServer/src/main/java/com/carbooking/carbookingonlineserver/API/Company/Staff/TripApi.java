package com.carbooking.carbookingonlineserver.API.Company.Staff;

import com.carbooking.carbookingonlineserver.dto.repuest.TripRequest;
import com.carbooking.carbookingonlineserver.entity.DetailDropOffLocation;
import com.carbooking.carbookingonlineserver.entity.Trip;
import com.carbooking.carbookingonlineserver.service.TripService;
import com.carbooking.carbookingonlineserver.utils.CloudinaryService;
import com.carbooking.carbookingonlineserver.utils.TripFile;
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
public class TripApi {
    @Autowired
    private TripService tripService;
    @Autowired
    private TripFile tripFile;
    @Autowired
    private CloudinaryService cloudinaryService;


    @PostMapping("/trip/list")
    public ResponseEntity<?> uploadListFile(@RequestParam("file") MultipartFile file){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            List<Trip> list = tripFile.readCsvData(reader);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi nếu cần
        }
        String Message = "Không thể upload fileTrip!";
        return new ResponseEntity<>(Message, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/trip")
    public ResponseEntity<?> addTrip(@RequestBody TripRequest tripRequest){
        Trip trip = tripService.addTrip(tripRequest);
        return new ResponseEntity<>(trip, HttpStatus.OK);
    }

    @PutMapping("trip/{idtrip}")
    public ResponseEntity<?> updateTrip(@PathVariable Long idtrip,@RequestBody TripRequest tripRequest){
        Trip trip = tripService.updateTrip(idtrip,tripRequest);
        return new ResponseEntity<>(trip, HttpStatus.OK);
    }

    @PutMapping("trip/status")
    public ResponseEntity<?> updateStatusTrip(@RequestParam Long idtrip){
        Boolean istrip = tripService.updateStatusTrip(idtrip);
        return new ResponseEntity<>(istrip, HttpStatus.OK);
    }


    @GetMapping("/trip/{idtrip}")
    public ResponseEntity<?> getIdTrip(@PathVariable Long idtrip){
        Trip trip = tripService.getTripById(idtrip);
        return new ResponseEntity<>(trip, HttpStatus.OK);
    }

    @GetMapping("/trip/company/{idcompany}")
    public ResponseEntity<?> getListCompanyTrip(@PathVariable String idcompany){
        List<Trip> trips = tripService.listTripOfCompany(idcompany);
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @GetMapping("/trip/locate")
    public ResponseEntity<?> getTripLocate(@RequestParam String pickup, @RequestParam String dropoff){
        List<Trip> trips = tripService.getTripByPickUpAndDropOff(pickup,dropoff);
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @PutMapping("trip/promotion")
    public ResponseEntity<?> UpdateTripPromotions(@RequestParam Long idtrip, @RequestParam Long idpromotion){
        tripService.UpdatePromotions(idtrip,idpromotion);
        String Message = "update thành công";
        return new ResponseEntity<>(Message, HttpStatus.OK);
    }

    @PostMapping("/trip/updateimage")
    public ResponseEntity<?> uploadImage(@RequestParam("id") Long id,@RequestParam("file")MultipartFile file){
        String data = cloudinaryService.upload(file);
        Trip trip = tripService.updateTripImageUrl(id,data);
        if(trip != null){
            return new ResponseEntity<>(trip, HttpStatus.OK);
        }
        String Message = "update hình ảnh không thành công";
        return new ResponseEntity<>(Message, HttpStatus.OK);
    }
}
