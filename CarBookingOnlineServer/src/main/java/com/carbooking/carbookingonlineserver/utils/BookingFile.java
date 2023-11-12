package com.carbooking.carbookingonlineserver.utils;

import com.carbooking.carbookingonlineserver.dto.reponse.BookingReponse;
import com.carbooking.carbookingonlineserver.dto.repuest.BookingRequest;
import com.carbooking.carbookingonlineserver.dto.repuest.CarRequest;
import com.carbooking.carbookingonlineserver.entity.Booking;
import com.carbooking.carbookingonlineserver.entity.Car;
import com.carbooking.carbookingonlineserver.service.BookingService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Service
public class BookingFile {
    @Autowired
    private BookingService bookingService;
    public List<BookingReponse> readCsvData(Reader reader) throws IOException {
        CsvToBean<BookingRequest> csvToBean = new CsvToBeanBuilder<BookingRequest>(reader)
                .withType(BookingRequest.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        List<BookingRequest> list = csvToBean.parse();
        for (BookingRequest bookingRequest: list){
            bookingRequest.setPhoneUser("0"+bookingRequest.getPhoneUser());
        }
        return bookingService.addListBooking(list);
    }
}
