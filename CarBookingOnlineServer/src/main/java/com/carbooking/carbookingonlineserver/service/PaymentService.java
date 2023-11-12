package com.carbooking.carbookingonlineserver.service;

import com.carbooking.carbookingonlineserver.entity.Booking;
import com.carbooking.carbookingonlineserver.entity.Payment;

public interface PaymentService {
    Payment addPayment(Payment payment);
    Boolean UpdateStatusPayment(Long id, String status);
    Payment findPaymentByBooking(Long id);
}
