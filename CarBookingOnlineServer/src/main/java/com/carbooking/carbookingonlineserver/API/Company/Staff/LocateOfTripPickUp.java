package com.carbooking.carbookingonlineserver.API.Company.Staff;

import com.carbooking.carbookingonlineserver.dto.repuest.LocateDetailRequest;
import com.carbooking.carbookingonlineserver.dto.repuest.TripRequest;
import com.carbooking.carbookingonlineserver.entity.DetailPickUpLocation;
import com.carbooking.carbookingonlineserver.service.DetailPickUpLocationService;
import com.carbooking.carbookingonlineserver.utils.DetailLocateFile;
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
public class LocateOfTripPickUp {

    @Autowired
    private DetailLocateFile csvService;

    @Autowired
    private DetailPickUpLocationService detailPickUpLocationService;


    @PostMapping("/pickup")
    public ResponseEntity<?> uploadLocate(@RequestBody LocateDetailRequest locateDetailRequest){
        DetailPickUpLocation detailPickUpLocation = detailPickUpLocationService.addOne(locateDetailRequest);
        return  new ResponseEntity<>(detailPickUpLocation, HttpStatus.OK);
    }

    @PutMapping("/pickup/{id}")
    public ResponseEntity<?> updatePickUp(@PathVariable Long id,@RequestBody LocateDetailRequest locateDetailRequest){
        DetailPickUpLocation list = detailPickUpLocationService.editLocate(id,locateDetailRequest);
        if(list == null){
            String Message = "Không thể cập nhật!";
            return new ResponseEntity<>(Message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/pickup/{id}")
    public ResponseEntity<?> getOneByid(@PathVariable Long id){
        DetailPickUpLocation detailPickUpLocation = detailPickUpLocationService.getDetailByil(id);
        if(detailPickUpLocation != null){
            return  new ResponseEntity<>(detailPickUpLocation, HttpStatus.OK);
        }
        String Message = "Không thể cập nhật!";
        return new ResponseEntity<>(Message, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/pickup/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        detailPickUpLocationService.delete(id);
        String Message = "Xóa thành công vị trí chi tiết!";
        return new ResponseEntity<>(Message, HttpStatus.OK);
    }

    @GetMapping("pickup/trip/{id}")
    public ResponseEntity<?> getOneAllByidTrip(@PathVariable Long id){
        List<DetailPickUpLocation> list = detailPickUpLocationService.getallOftrip(id);
        if(list != null){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        String Message = "Không tìm thấy chuyến đi!";
        return new ResponseEntity<>(Message, HttpStatus.OK);
    }




    @PostMapping("/pickup/list")
    public ResponseEntity<?> uploadListFile(@RequestParam("file") MultipartFile file){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            List<DetailPickUpLocation> list = csvService.readCsvData(reader);
            if(list == null){
                String Message = "Không thể upload file! kiểm tra lại thông tin file";
                return new ResponseEntity<>(Message, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi nếu cần
        }
        String Message = "Không thể upload file!";
        return new ResponseEntity<>(Message, HttpStatus.BAD_REQUEST);
    }
}
