package com.carbooking.carbookingonlineserver.service.implservice;

import com.carbooking.carbookingonlineserver.dto.repuest.TypeCarRequest;
import com.carbooking.carbookingonlineserver.entity.Car;
import com.carbooking.carbookingonlineserver.entity.Company;
import com.carbooking.carbookingonlineserver.entity.TypeCar;
import com.carbooking.carbookingonlineserver.repository.TypeCarRepository;
import com.carbooking.carbookingonlineserver.service.TypeCarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImplTypeCar implements TypeCarService {
    @Autowired
    private TypeCarRepository typeCarRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public TypeCar addTypecar(TypeCarRequest typeCarRequest) {
        TypeCar typeCar = mapper.map(typeCarRequest, TypeCar.class);
        return typeCarRepository.save(typeCar);
    }

    @Override
    public TypeCar GetTypecar(Long id) {
        Optional<TypeCar> typeCar = typeCarRepository.findById(id);
        if(typeCar.isPresent()){
            return typeCar.get();
        }
        return null;
    }

    @Override
    public TypeCar deleteTypecar(Long id) {
        Optional<TypeCar> typeCar = typeCarRepository.findById(id);
        if(typeCar.isPresent()){
            TypeCar typeCar1 = typeCar.get();
            typeCar1.setStatus(false);
            return  typeCarRepository.save(typeCar1);
        }
        return null;
    }

    @Override
    public Boolean checkExist(Long id) {
        return typeCarRepository.existsById(id);
    }

    @Override
    public TypeCar EditTypecar(Long id, TypeCarRequest typeCarRequest) {
        typeCarRequest.setId(id);
        Optional<TypeCar> typeCar = typeCarRepository.findById(id);
        if(typeCar.isPresent()){
            TypeCar typeCar1 = typeCar.get();
            mapper.map(typeCarRequest,typeCar1);
            return  typeCarRepository.save(typeCar1);
        }
        return null;
    }

    @Override
    public List<TypeCar> getAll() {
        return typeCarRepository.findAll();
    }

    @Override
    public List<TypeCar> addListTypeCar(List<TypeCarRequest> list_) {
        List<TypeCar> list  = new ArrayList<>();
        for(int i = 0; i < list_.size(); i++){
            TypeCar car = mapper.map(list_.get(i), TypeCar.class);
            list.add(car);
        }
        List<TypeCar> list__ = typeCarRepository.saveAll(list);
        return list__;
    }
}
