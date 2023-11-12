package com.carbooking.carbookingonlineserver.API.User;

import com.carbooking.carbookingonlineserver.dto.reponse.BookingReponse;
import com.carbooking.carbookingonlineserver.dto.repuest.BookingRequest;
import com.carbooking.carbookingonlineserver.entity.Booking;
import com.carbooking.carbookingonlineserver.service.BookingService;
import com.carbooking.carbookingonlineserver.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:3000")
public class BookingAPI {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private IMailService mailService;

    @PostMapping("/booking")
    public ResponseEntity<?> addOnePost(@RequestBody BookingRequest bookingRequest){
        Calendar calendar = Calendar.getInstance(); // Lấy một thể hiện của lớp Calendar
        Date currentDate = calendar.getTime();
        bookingRequest.setDate(currentDate);
        BookingReponse booking = bookingService.addBooking(bookingRequest);
        if(booking != null){
            try {
                mailService.sendHtmlEmail(booking.getDrivertrip().getPickupLocation(), booking.getDrivertrip().getDropoffLocation()
                        , booking.getDate().toString(), String.valueOf(booking.getFareAmount()),
                        booking.getUser().getEmail());
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            return new ResponseEntity<>(booking, HttpStatus.OK);
        }
        String message = "Không thể đặt chỗ!";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/booking/{id}")
    public ResponseEntity<?> getBooking(@PathVariable Long id){

        BookingReponse booking = bookingService.getBooking(id);
        if(booking != null){
            return new ResponseEntity<>(booking, HttpStatus.OK);
        }
        String message = "Không lấy Booking!";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/booking/trip")
    public ResponseEntity<?> getBookingListDriverTrip(@RequestParam Long iddrivertrip){

        List<BookingReponse> booking = bookingService.getListBookingByDriverTrip(iddrivertrip);
        if(booking != null){
            return new ResponseEntity<>(booking, HttpStatus.OK);
        }
        String message = "Không lấy list Booking của driver trip!";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/booking/user")
    public ResponseEntity<?> getBookingListUser(@RequestParam String phoneuser){

        List<BookingReponse> booking = bookingService.getListBookingByUser(phoneuser);
        if(booking != null){
            return new ResponseEntity<>(booking, HttpStatus.OK);
        }
        String message = "Không lấy list Booking của user!";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

}
