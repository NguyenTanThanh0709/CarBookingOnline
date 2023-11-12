package com.carbooking.carbookingonlineserver.API.User;

import com.carbooking.carbookingonlineserver.entity.Booking;
import com.carbooking.carbookingonlineserver.entity.Payment;
import com.carbooking.carbookingonlineserver.repository.PaymentRepository;
import com.carbooking.carbookingonlineserver.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentAPI {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/payment/{idbooking}")
    public ResponseEntity<?> getPaymentByBookingId(@PathVariable Long idbooking) {
        Booking booking = bookingService.getBookingById(idbooking);

        if (booking == null) {
            return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
        }

        Optional<Payment> payment = paymentRepository.findByBooking(booking);

        if (payment.isPresent()) {
            return new ResponseEntity<>(payment.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Payment not found for the booking", HttpStatus.NOT_FOUND);
        }
    }
}
