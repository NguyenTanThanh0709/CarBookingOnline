package com.carbooking.carbookingonlineserver.utils;

import com.carbooking.carbookingonlineserver.dto.repuest.EmployeeRequest;
import com.carbooking.carbookingonlineserver.dto.repuest.LocateDetailRequest;
import com.carbooking.carbookingonlineserver.dto.repuest.TripRequest;
import com.carbooking.carbookingonlineserver.entity.DetailPickUpLocation;
import com.carbooking.carbookingonlineserver.entity.Trip;
import com.carbooking.carbookingonlineserver.service.TripService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Service
public class TripFile {
    @Autowired
    private TripService tripService;
    public List<Trip> readCsvData(Reader reader) throws IOException {
        CsvToBean<TripRequest> csvToBean = new CsvToBeanBuilder<TripRequest>(reader)
                .withType(TripRequest.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        List<TripRequest> list = csvToBean.parse();
        for (TripRequest employeeRequest: list){
            employeeRequest.setPhoneCompany("0"+employeeRequest.getPhoneCompany());
        }
        return tripService.addListTrip(list);
    }
}
