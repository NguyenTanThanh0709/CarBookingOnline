package com.carbooking.carbookingonlineserver.utils;

import com.carbooking.carbookingonlineserver.dto.repuest.CarRequest;
import com.carbooking.carbookingonlineserver.dto.repuest.CompanyRepuest;
import com.carbooking.carbookingonlineserver.entity.Car;
import com.carbooking.carbookingonlineserver.entity.Company;
import com.carbooking.carbookingonlineserver.service.CarService;
import com.carbooking.carbookingonlineserver.service.CompanyService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Service
public class CompanyFile {
    @Autowired
    private CompanyService companyService;
    public List<Company> readCsvData(Reader reader) throws IOException {
        CsvToBean<CompanyRepuest> csvToBean = new CsvToBeanBuilder<CompanyRepuest>(reader)
                .withType(CompanyRepuest.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        List<CompanyRepuest> list = csvToBean.parse();
        for (CompanyRepuest bookingRequest: list){
            String phone = bookingRequest.getPhone().replace("_","");
            bookingRequest.setPhone(phone);
        }
        return companyService.addlist(list);
    }
}
