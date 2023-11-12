package com.carbooking.carbookingonlineserver.service.implservice;

import com.carbooking.carbookingonlineserver.dto.Role;
import com.carbooking.carbookingonlineserver.dto.repuest.CompanyRepuest;
import com.carbooking.carbookingonlineserver.entity.Car;
import com.carbooking.carbookingonlineserver.entity.Company;
import com.carbooking.carbookingonlineserver.entity.TypeCar;
import com.carbooking.carbookingonlineserver.entity.User;
import com.carbooking.carbookingonlineserver.repository.CompanyRepository;
import com.carbooking.carbookingonlineserver.repository.UserRepository;
import com.carbooking.carbookingonlineserver.service.CompanyService;
import com.carbooking.carbookingonlineserver.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImplCompany implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userService;

    @Autowired
    private UserService userService_;

    @Override
    public Company delete(String phone) {
        Optional<Company> company_ = companyRepository.findByPhone(phone);
        if(company_.isPresent()){
            Company company = company_.get();
            company.setStatus(false);
            companyRepository.save(company);
            return company;
        }else {
            return null;
        }

    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    private void addOwnerCompany(Company company){
        User user = new User();
        user.setPhone(company.getPhone());
        user.setEmail(company.getEmail());
        user.setPassword(passwordEncoder.encode("password"));
        user.setName(company.getName());
        user.setStatus(true);
        user.setRole(Role.OWNER);
        user.setCompany(company);
        userService.save(user);
    }


    @Override
    public Company AddAndEditCompany(CompanyRepuest company) {
        Company company_ = modelMapper.map(company, Company.class);
        company_ = companyRepository.save(company_);
        addOwnerCompany(company_);
        return company_;
    }

    @Override
    public Company edit(CompanyRepuest company) {
        Optional<Company> company_ = companyRepository.findByPhone(company.getPhone());
        if(company_.isPresent()){
            Company company1 = company_.get();
            modelMapper.map(company,company1);
            return companyRepository.save(company1);
        }else {
            return null;
        }
    }


    @Override
    public Company getCompany(String phone) {
        Optional<Company> company = companyRepository.findByPhone(phone);
        if(company.isPresent()){
            return company.get();
        }else {
            return null;
        }
    }

    @Override
    public Company getCompanyByStaff(String phone) {
        return userService_.findByPhone(phone).getCompany();
    }

    @Override
    public List<Company> getAllCmpanyActivy() {
        List<Company> list = companyRepository.findAllByStatus(true);
        return list;
    }

    @Override
    public List<Company> getAllCmpanyINActivy() {
        List<Company> list = companyRepository.findAllByStatus(false);
        return list;
    }

    @Override
    public List<Company> addlist(List<CompanyRepuest> carRequests) {
        List<Company> list  = new ArrayList<>();
        for(int i = 0; i < carRequests.size(); i++){
            if(getCompany(carRequests.get(i).getPhone()) != null){
                return null;
            }
            Company car = modelMapper.map(carRequests.get(i), Company.class);
            list.add(car);
        }
        List<Company> list_ = companyRepository.saveAll(list);
        for (Company company: list_){
            addOwnerCompany(company);
        }
        return list_;
    }

}
