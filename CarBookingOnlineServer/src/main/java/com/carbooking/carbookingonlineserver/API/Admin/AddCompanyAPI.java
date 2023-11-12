package com.carbooking.carbookingonlineserver.API.Admin;

import com.carbooking.carbookingonlineserver.dto.repuest.CompanyRepuest;
import com.carbooking.carbookingonlineserver.entity.Company;
import com.carbooking.carbookingonlineserver.entity.DriverTrip;
import com.carbooking.carbookingonlineserver.service.CompanyService;
import com.carbooking.carbookingonlineserver.utils.CompanyFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v2/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AddCompanyAPI {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyFile companyFile;

    @PostMapping("/companies")
    public ResponseEntity<?> addCompany(@RequestBody CompanyRepuest company) {
        Company existingCompany = companyService.getCompany(company.getPhone());
        if (existingCompany != null) {
            // Nếu một công ty với cùng số điện thoại đã tồn tại, trả về một thông báo lỗi.
            String errorMessage = "Công ty với số điện thoại này đã tồn tại.";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        Company newCompany = companyService.AddAndEditCompany(company);
        // Trả về thông báo thành công và đối tượng công ty đã tạo hoặc sửa đổi.
        return new ResponseEntity<>(newCompany, HttpStatus.OK);
    }




    @GetMapping("/companies/{phone}")
    public ResponseEntity<Company> getCompanyByPhone(@PathVariable String phone) {
        Company company = companyService.getCompany(phone);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getListCompanyActivy(@RequestParam Boolean status){
        List<Company> list = new ArrayList<>();
        if(status == true){
            list = companyService.getAllCmpanyActivy();
        }else {
            list = companyService.getAllCmpanyINActivy();
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/companies/{phone}")
    public ResponseEntity<Company> updateCompany(@PathVariable String phone, @RequestBody CompanyRepuest updatedCompany) {
        Company existingCompany = companyService.getCompany(phone);
        if(existingCompany == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            updatedCompany.setPhone(phone);
            Company updated = companyService.edit(updatedCompany);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
    }

    @DeleteMapping("/companies/{phone}")
    public ResponseEntity<Company> deleteCompany(@PathVariable String phone) {
        Company company = companyService.delete(phone);
        if(company != null){
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @PostMapping("/companies/list")
    public ResponseEntity<?> uploadListFile(@RequestParam("file") MultipartFile file){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            List<Company> list = companyFile.readCsvData(reader);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi nếu cần
        }
        String Message = "Không thể upload file company!";
        return new ResponseEntity<>(Message, HttpStatus.BAD_REQUEST);
    }


}
