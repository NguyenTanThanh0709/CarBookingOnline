package com.carbooking.carbookingonlineserver.service.implservice;

import com.carbooking.carbookingonlineserver.entity.Booking;
import com.carbooking.carbookingonlineserver.entity.Payment;
import com.carbooking.carbookingonlineserver.repository.PaymentRepository;
import com.carbooking.carbookingonlineserver.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class implPayment implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public Payment addPayment(Payment payment) {

        return paymentRepository.save(payment);
    }

    @Override
    public Boolean UpdateStatusPayment(Long id, String status) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if(optionalPayment.isPresent()){
            Payment payment = optionalPayment.get();
            payment.setStatus(status);
            paymentRepository.save(payment);
            return true;
        }
        return false;
    }

    @Override
    public Payment findPaymentByBooking(Long id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if(optionalPayment.isPresent()){
            return optionalPayment.get();
        }
        return  null;
    }
}
