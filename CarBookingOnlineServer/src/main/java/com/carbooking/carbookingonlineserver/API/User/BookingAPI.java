package com.carbooking.carbookingonlineserver.API.User;

import com.carbooking.carbookingonlineserver.dto.reponse.BookingReponse;
import com.carbooking.carbookingonlineserver.dto.repuest.BookingRequest;
import com.carbooking.carbookingonlineserver.entity.Booking;
import com.carbooking.carbookingonlineserver.entity.Promotions;
import com.carbooking.carbookingonlineserver.entity.Trip;
import com.carbooking.carbookingonlineserver.repository.PromotionsRepository;
import com.carbooking.carbookingonlineserver.service.BookingService;
import com.carbooking.carbookingonlineserver.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private PromotionsRepository promotionsRepository;

    @GetMapping("/promotion/byCodeAndTripId/{code}/{tripId}")
    public ResponseEntity<?> getPromotionByCodeAndTripId(@PathVariable String code, @PathVariable Long tripId) {
        Optional<Promotions> promotion = promotionsRepository.findByCode(code);

        if (promotion.isPresent()) {
            if (isValidPromotionForTrip(promotion.get(), tripId,promotion.get().getStartDate(), promotion.get().getEndDate())) {
                return new ResponseEntity<>(promotion.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    private boolean isValidPromotionForTrip(Promotions promotion, Long tripId, Date startDate, Date endDate) {
        // Check if the promotion is valid for the given tripId
        List<Trip> trips = promotion.getTrips();
        LocalDate currentDate = LocalDate.now();
        LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Check if the current date is within the range

        if((!currentDate.isBefore(startLocalDate) && !currentDate.isAfter(endLocalDate))&& (trips != null && trips.stream().anyMatch(trip -> trip.getId().equals(tripId)))){
            return true;
        }else {
            return false;
        }

    }

}
