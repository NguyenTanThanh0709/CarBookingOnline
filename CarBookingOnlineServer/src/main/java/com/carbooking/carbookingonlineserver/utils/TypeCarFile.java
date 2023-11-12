package com.carbooking.carbookingonlineserver.utils;

import com.carbooking.carbookingonlineserver.dto.repuest.TripRequest;
import com.carbooking.carbookingonlineserver.dto.repuest.TypeCarRequest;
import com.carbooking.carbookingonlineserver.entity.Trip;
import com.carbooking.carbookingonlineserver.entity.TypeCar;
import com.carbooking.carbookingonlineserver.service.TripService;
import com.carbooking.carbookingonlineserver.service.TypeCarService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Service
public class TypeCarFile {
    @Autowired
    private TypeCarService typeCarService;
    public List<TypeCar> readCsvData(Reader reader) throws IOException {
        CsvToBean<TypeCarRequest> csvToBean = new CsvToBeanBuilder<TypeCarRequest>(reader)
                .withType(TypeCarRequest.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        List<TypeCarRequest> list = csvToBean.parse();
        return typeCarService.addListTypeCar(list);
    }
}
