package com.carbooking.carbookingonlineserver.service;

import com.carbooking.carbookingonlineserver.dto.repuest.CarRequest;
import com.carbooking.carbookingonlineserver.entity.Car;

import java.util.List;

public interface CarService {
    Car add(CarRequest carRequest);
    Void setAvailble(Boolean availability, String id);
    Boolean checkExistLince(String Lince);
    Car edit(String id,CarRequest carRequest);
    Car getCarById(String id);
    List<Car> getAllByCompany(String phoneCompany);
    List<Car> getAllByTypeAndCompany(Long typecar,String phoneCompany);
    List<Car> addListCar(List<CarRequest> carRequests);
}
