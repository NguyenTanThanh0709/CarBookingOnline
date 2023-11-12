package com.carbooking.carbookingonlineserver.utils;

import com.carbooking.carbookingonlineserver.dto.repuest.BookingRequest;
import com.carbooking.carbookingonlineserver.dto.repuest.CarRequest;
import com.carbooking.carbookingonlineserver.dto.repuest.TripRequest;
import com.carbooking.carbookingonlineserver.entity.Car;
import com.carbooking.carbookingonlineserver.entity.Trip;
import com.carbooking.carbookingonlineserver.service.CarService;
import com.carbooking.carbookingonlineserver.service.TripService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Service
public class CarFile {
    @Autowired
    private CarService carService;
    public List<Car> readCsvData(Reader reader) throws IOException {
        CsvToBean<CarRequest> csvToBean = new CsvToBeanBuilder<CarRequest>(reader)
                .withType(CarRequest.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        List<CarRequest> list = csvToBean.parse();
        for (CarRequest bookingRequest: list){
            bookingRequest.setPhoneCompanyId("0"+bookingRequest.getPhoneCompanyId());
        }
        return carService.addListCar(list);
    }
}
