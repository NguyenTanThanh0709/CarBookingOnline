package com.carbooking.carbookingonlineserver.API.Company.Staff;
import com.carbooking.carbookingonlineserver.dto.Role;
import com.carbooking.carbookingonlineserver.dto.repuest.AddListDriverForTripRequest;
import com.carbooking.carbookingonlineserver.dto.repuest.DriverTripRequest;
import com.carbooking.carbookingonlineserver.dto.repuest.ListDriverTripDTO;
import com.carbooking.carbookingonlineserver.entity.Car;
import com.carbooking.carbookingonlineserver.entity.DriverTrip;
import com.carbooking.carbookingonlineserver.entity.Trip;
import com.carbooking.carbookingonlineserver.entity.User;
import com.carbooking.carbookingonlineserver.repository.DriverTripRepository;
import com.carbooking.carbookingonlineserver.service.CarService;
import com.carbooking.carbookingonlineserver.service.DriverTripService;
import com.carbooking.carbookingonlineserver.service.TripService;
import com.carbooking.carbookingonlineserver.service.UserService;
import com.carbooking.carbookingonlineserver.service.implservice.UserDriverTripService;
import com.carbooking.carbookingonlineserver.utils.DrivertripFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/staff")
@CrossOrigin(origins = "http://localhost:3000")
public class DriverTripAPI {
    @Autowired
    private DriverTripService driverTripService;

    @Autowired
    private CarService carService;

    @Autowired
    private DrivertripFile drivertripFile;

    @Autowired
    private UserDriverTripService userDriverTripService;

    @Autowired
    private UserService userService;

    @Autowired
    private DriverTripRepository driverTripRepository;

    @Autowired
    private TripService tr;

    @GetMapping("/by-driver-trip/{driverTripId}")
    public ResponseEntity<List<User>> getUserDriverTripsByDriverTrip(@PathVariable Long driverTripId) {
        DriverTrip driverTrip = new DriverTrip();
        driverTrip.setId(driverTripId); // Tạo một đối tượng DriverTrip với ID đã cung cấp

        List<User> userDriverTrips = userDriverTripService.findUserDriverTripsByDriverTrip(driverTrip);

        if (userDriverTrips.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userDriverTrips);
    }


    @PostMapping("/drivertrip")
    public ResponseEntity<?> addOnePost(@RequestBody DriverTripRequest driverTripRequest){
        Date currentDate = new Date();
        Date dateToCheck = driverTripRequest.getDate();
        if (dateToCheck.before(currentDate) || dateToCheck.equals(currentDate)) {
            String message ="Kiểm tra lại ngày di chuyển!";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        DriverTrip driverTrip = driverTripService.addOneTrip(driverTripRequest);
        if(driverTrip != null){
            return new ResponseEntity<>(driverTrip, HttpStatus.OK);
        }
        String message = "Không thể thêm chuyến đi mới !";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/drivertrip/{id}")
    public ResponseEntity<?> getOnePostById(@PathVariable Long id){
        DriverTrip driverTrip = driverTripService.getOne(id);
        if(driverTrip != null){
            return new ResponseEntity<>(driverTrip, HttpStatus.OK);
        }
        String message = "Không thể tìm được chuyến đi !";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/drivertrip/{id}")
    public ResponseEntity<?> editOnePost(@PathVariable Long id,@RequestBody DriverTripRequest driverTripRequest){
        Date currentDate = new Date();
        Date dateToCheck = driverTripRequest.getDate();
        if (dateToCheck.before(currentDate) || dateToCheck.equals(currentDate)) {
            String message ="Kiểm tra lại ngày di chuyển!";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        DriverTrip driverTrip = driverTripService.editOneTrip(id,driverTripRequest);
        if(driverTrip != null){
            return new ResponseEntity<>(driverTrip, HttpStatus.OK);
        }
        String message = "Không thể update chuyến đi!";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/drivertrip")
    public ResponseEntity<?> updateStatus(@RequestParam Long id){
        return new ResponseEntity<>(driverTripService.updateStatus(id), HttpStatus.OK);
    }

    @GetMapping("/drivertrip/listbytrip")
    public ResponseEntity<?> getlistbydatetrip(@RequestParam Long idtrip){
        List<DriverTrip> list = driverTripService.findByTripAndate(idtrip);
        if(list != null){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        String message = "Không thể tìm được chuyến đi của chặng đi!" ;
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/drivertrip/listbydate")
    public ResponseEntity<?> getlistbydate(@RequestParam String phonecompany,@RequestParam @DateTimeFormat(pattern = "MM/dd/yyyy") Date date){
        List<DriverTrip> list = new ArrayList<>();
        list = driverTripRepository.findByDate(date);
        if(list != null && list.size() >0){
            List<DriverTrip> listTEMP = new ArrayList<>();
            for (DriverTrip driverTrip : list){
                if(driverTrip.getTrip().getPhoneCompany().getPhone().equals(phonecompany)){
                    listTEMP.add(driverTrip);
                }
            }
            return new ResponseEntity<>(listTEMP, HttpStatus.OK);
        }
        String message = "Không thể tìm được chuyến đi của ngày: " + date.toString() ;
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/drivertrip/listbygraterdatetrip")
    public ResponseEntity<?> getlistbygraterdatetrip(@RequestParam Long idtrip,@RequestParam @DateTimeFormat(pattern = "MM/dd/yyyy") Date date){
        List<DriverTrip> list = driverTripService.findByDateGreaterThanAndTrip(date,idtrip);
        if(list != null){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        String message = "Không thể tìm được chuyến đi trước ngày " + date.toString();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/drivertrip/listtripdategreate")
    public ResponseEntity<?> getlistbygraterdatetrip_(@RequestParam Long idtrip,@RequestParam @DateTimeFormat(pattern = "MM/dd/yyyy") Date date){
        Trip trip = tr.getTripById(idtrip);
        if(trip != null){
            List<DriverTrip> list = driverTripRepository.findDriverTripsByTripAndDateAfter(trip,date);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        String message = "Không thể tìm được chuyến đi sau ngày " + date.toString();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/drivertrip/listbydategrate")
    public ResponseEntity<?> listbydategrate(@RequestParam String phonecompany,@RequestParam @DateTimeFormat(pattern = "MM/dd/yyyy") Date date){
        List<DriverTrip> list = new ArrayList<>();
        list = driverTripRepository.findByDateGreaterThanEqual(date);
        if(list != null && list.size() >0){
            List<DriverTrip> listTEMP = new ArrayList<>();
            for (DriverTrip driverTrip : list){
                if(driverTrip.getTrip().getPhoneCompany().getPhone().equals(phonecompany)){
                    listTEMP.add(driverTrip);
                }
            }
            return new ResponseEntity<>(listTEMP, HttpStatus.OK);
        }
        String message = "Không thể tìm được chuyến đi của ngày: " + date.toString() ;
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/drivertrip/list")
    public ResponseEntity<?> uploadListFile(@RequestParam("file") MultipartFile file){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            List<DriverTrip> list = drivertripFile.readCsvData(reader);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi nếu cần
        }
        String Message = "Không thể upload file driverTrip!";
        return new ResponseEntity<>(Message, HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/drivertrip/add-update-car")
    public  ResponseEntity<DriverTrip> addOrUpdateCarDriverTripCar(
            @RequestParam("idCar") String idCar,
            @RequestParam("idDriverTrip") Long idDriverTrip
    ){
        DriverTrip driverTrip = driverTripService.addOrUpdateCarDriverTripCar(idCar, idDriverTrip);
        if (driverTrip != null) {
            return ResponseEntity.ok(driverTrip);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/drivertrip/add-list-drivers")
    public ResponseEntity<DriverTrip> addListDriverForDriverTrip(
            @RequestBody AddListDriverForTripRequest addListDriverForTripRequest
    ){
        DriverTrip driverTrip = driverTripService.addListDriverForDriverTrip(addListDriverForTripRequest);
        if (driverTrip != null) {
            return ResponseEntity.ok(driverTrip);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/drivertrip/remove-driver")
    public ResponseEntity<?> removeDriver(
            @RequestParam("phoneDriver") String phoneDriver,
            @RequestParam("id") Long id
    ) {
       driverTripService.removeDriver(phoneDriver, id);
        String Message = "Xóa OK";
        return new ResponseEntity<>(Message, HttpStatus.ACCEPTED);
    }

    @GetMapping("/drivertrip/employees")
    public ResponseEntity<List<User>> getAllEmployeeByCompanyAndRole(@RequestParam Role role, @RequestParam String phoneCompany){
        List<User> list = new ArrayList<>();
        list = userService.getAllEmployeeByCompanyAndRole(role,phoneCompany);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/addListBetweenDay")
    public ResponseEntity<List<DriverTrip>> addListBetweenDay(@RequestBody ListDriverTripDTO requestDTO) {
        List<DriverTrip> result = driverTripService.addListBetweenDay(
                requestDTO.getPhoneCompany(),
                requestDTO.getIdTrip(),
                requestDTO.getStart(),
                requestDTO.getEnd(),
                requestDTO.getTypecar()
        );

        if (result != null && !result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            // You can customize the response status and message based on your requirements
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


}
