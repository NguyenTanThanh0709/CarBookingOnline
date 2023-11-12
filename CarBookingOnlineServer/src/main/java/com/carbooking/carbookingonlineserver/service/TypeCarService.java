package com.carbooking.carbookingonlineserver.service;

import com.carbooking.carbookingonlineserver.dto.repuest.TypeCarRequest;
import com.carbooking.carbookingonlineserver.entity.TypeCar;

import java.util.List;

public interface TypeCarService {
    TypeCar addTypecar(TypeCarRequest typeCarRequest);
    TypeCar GetTypecar(Long id);
    TypeCar deleteTypecar(Long id);
    Boolean checkExist(Long id);
    TypeCar EditTypecar(Long id, TypeCarRequest typeCarRequest);
    List<TypeCar> getAll();

    List<TypeCar> addListTypeCar(List<TypeCarRequest> list);
}
