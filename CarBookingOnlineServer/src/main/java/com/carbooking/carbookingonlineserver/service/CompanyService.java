package com.carbooking.carbookingonlineserver.service;

import com.carbooking.carbookingonlineserver.dto.repuest.CompanyRepuest;
import com.carbooking.carbookingonlineserver.entity.Company;

import java.util.List;

public interface CompanyService {
    Company delete(String phone);
    Company AddAndEditCompany(CompanyRepuest company);
    Company edit(CompanyRepuest company);
    Company getCompany(String phone);
    Company getCompanyByStaff(String phone);
    List<Company> getAllCmpanyActivy();
    List<Company> getAllCmpanyINActivy();

    List<Company> addlist(List<CompanyRepuest> list);
}
