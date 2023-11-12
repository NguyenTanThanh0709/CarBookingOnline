package com.carbooking.carbookingonlineserver.utils;

import com.carbooking.carbookingonlineserver.dto.repuest.DriverTripRequest;
import com.carbooking.carbookingonlineserver.entity.DriverTrip;
import com.carbooking.carbookingonlineserver.service.DriverTripService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Service
public class DrivertripFile {
    @Autowired
    private DriverTripService driverTripService;
    public List<DriverTrip> readCsvData(Reader reader) throws IOException {
        CsvToBean<DriverTripRequest> csvToBean = new CsvToBeanBuilder<DriverTripRequest>(reader)
                .withType(DriverTripRequest.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        List<DriverTripRequest> list = csvToBean.parse();

        for (DriverTripRequest driverTripRequest: list){
            boolean containsHyphen = driverTripRequest.getDrivers().contains("-");
            if(!containsHyphen){
                driverTripRequest.setDrivers("0"+driverTripRequest.getDrivers());
            }
        }
        return driverTripService.addList(list);
    }
}
