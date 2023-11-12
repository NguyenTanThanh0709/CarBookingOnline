package com.carbooking.carbookingonlineserver.service.implservice;

import com.carbooking.carbookingonlineserver.dto.repuest.CarRequest;
import com.carbooking.carbookingonlineserver.entity.Car;
import com.carbooking.carbookingonlineserver.entity.Company;
import com.carbooking.carbookingonlineserver.entity.Trip;
import com.carbooking.carbookingonlineserver.entity.TypeCar;
import com.carbooking.carbookingonlineserver.repository.CarRepository;
import com.carbooking.carbookingonlineserver.service.CarService;
import com.carbooking.carbookingonlineserver.service.CompanyService;
import com.carbooking.carbookingonlineserver.service.SeatService;
import com.carbooking.carbookingonlineserver.service.TypeCarService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImplCar implements CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private TypeCarService typeCarService;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private SeatService seatService;

    @Override
    public Car add(CarRequest carRequest) {
        Car car = mapper.map(carRequest, Car.class);
        Company company = companyService.getCompany(carRequest.getPhoneCompanyId());
        TypeCar typeCar = typeCarService.GetTypecar(carRequest.getTypeCarId());
        car.setTypeCar(typeCar);
        car.setPhoneCompany(company);
        Car car1 = carRepository.save(car);
        seatService.insertListSeatForCar(car1);
        car1 = this.getCarById(car1.getId());
        return car1;
    }

    @Override
    @Transactional
    public Void setAvailble(Boolean availability, String id) {
        carRepository.setAvailabilityById(id,availability);
        return null;
    }

    @Override
    public Boolean checkExistLince(String Lince) {
        return carRepository.existsByLicenseplates(Lince);
    }


    @Override
    @Transactional
    public Car edit(String id, CarRequest carRequest) {
        carRequest.setId(id);
        Optional<Car> car1 = carRepository.findByLicenseplates(id);
        if(car1.isPresent()){
            Car car = car1.get();
            mapper.map(carRequest,car);
            return  carRepository.save(car);
        }
        return null;
    }

    @Override
    public Car getCarById(String id) {
        Optional<Car> car = carRepository.findByLicenseplates(id);
        if(car.isPresent()){
            return car.get();
        }
        return null;
    }

    @Override
    public List<Car> getAllByCompany(String phoneCompany) {
        Company company = companyService.getCompany(phoneCompany);
        if(company!= null){
            return carRepository.findByPhoneCompany(company);
        }
        return new ArrayList<>();
    }

    @Override
    public List<Car> getAllByTypeAndCompany(Long typecar, String phoneCompany) {
        Company company = companyService.getCompany(phoneCompany);
        TypeCar typeCar = typeCarService.GetTypecar(typecar);
        if(company != null && typeCar != null){
            return carRepository.findByTypeCarAndPhoneCompany(typeCar,company);
        }
        return new ArrayList<>();
    }

    public void addSeatForCarList(List<Car> list){
        for(Car car: list){
            seatService.insertListSeatForCar(car);
        }
    }



    @Override
    public List<Car> addListCar(List<CarRequest> carRequests) {
        List<Car> list  = new ArrayList<>();
        for(int i = 0; i < carRequests.size(); i++){
            if(checkExistLince(carRequests.get(i).getLicenseplates())){
                return null;
            }
            Car car = mapper.map(carRequests.get(i), Car.class);
            String phoneCompany = carRequests.get(i).getPhoneCompanyId();
            Company company = companyService.getCompany(phoneCompany);
            TypeCar typeCar = typeCarService.GetTypecar(carRequests.get(i).getTypeCarId());
            car.setTypeCar(typeCar);
            car.setPhoneCompany(company);
            car.setId(carRequests.get(i).getLicenseplates());
            list.add(car);
        }
        List<Car> list_ = carRepository.saveAll(list);
        addSeatForCarList(list_);

        return getAllByCompany(carRequests.get(0).getPhoneCompanyId());
    }
}
