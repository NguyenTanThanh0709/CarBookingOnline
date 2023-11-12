package com.carbooking.carbookingonlineserver.service;

import com.carbooking.carbookingonlineserver.dto.reponse.BookingReponse;
import com.carbooking.carbookingonlineserver.dto.repuest.BookingRequest;
import com.carbooking.carbookingonlineserver.entity.Booking;

import java.util.List;

public interface BookingService {
    BookingReponse addBooking(BookingRequest bookingRequest);
    BookingReponse getBooking(Long id);
    List<BookingReponse> getListBookingByDriverTrip(Long iddrivertrip);
    List<BookingReponse> getListBookingByUser(String phoneUser);

    List<BookingReponse> addListBooking(List<BookingRequest> list);
    void deleteBookingSeatByBookingId(Long id);
    void deleteByid(Long id);

    Booking getBookingById(Long id);


}
