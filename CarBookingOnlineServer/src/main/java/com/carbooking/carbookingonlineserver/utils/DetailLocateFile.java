package com.carbooking.carbookingonlineserver.utils;

import com.carbooking.carbookingonlineserver.dto.repuest.LocateDetailRequest;
import com.carbooking.carbookingonlineserver.entity.DetailDropOffLocation;
import com.carbooking.carbookingonlineserver.entity.DetailPickUpLocation;
import com.carbooking.carbookingonlineserver.service.DetailDropOffLocationService;
import com.carbooking.carbookingonlineserver.service.DetailPickUpLocationService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Service
public class DetailLocateFile {


    @Autowired
    private DetailPickUpLocationService detailPickUpLocationService;
    @Autowired
    private DetailDropOffLocationService detailDropOffLocationService;
    public List<DetailPickUpLocation> readCsvData(Reader reader) throws IOException {
        CsvToBean<LocateDetailRequest> csvToBean = new CsvToBeanBuilder<LocateDetailRequest>(reader)
                .withType(LocateDetailRequest.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        List<LocateDetailRequest> list = csvToBean.parse();
        return detailPickUpLocationService.addListLocate(list);
    }
    public List<DetailDropOffLocation> readCsvData_(Reader reader) throws IOException {
        CsvToBean<LocateDetailRequest> csvToBean = new CsvToBeanBuilder<LocateDetailRequest>(reader)
                .withType(LocateDetailRequest.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        List<LocateDetailRequest> list = csvToBean.parse();
        return detailDropOffLocationService.addListLocate(list);
    }
}
