package com.carbooking.carbookingonlineserver.service.implservice;

import com.carbooking.carbookingonlineserver.dto.Role;
import com.carbooking.carbookingonlineserver.dto.repuest.AddListDriverForTripRequest;
import com.carbooking.carbookingonlineserver.dto.repuest.DriverTripRequest;
import com.carbooking.carbookingonlineserver.entity.*;
import com.carbooking.carbookingonlineserver.repository.DriverTripRepository;
import com.carbooking.carbookingonlineserver.repository.UserDriverTripRepository;
import com.carbooking.carbookingonlineserver.repository.UserRepository;
import com.carbooking.carbookingonlineserver.service.*;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ImplDriverTrip implements DriverTripService {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private DriverTripRepository driverTripRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TripService tripService;
    @Autowired
    private CarService carService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDriverTripService userDriverTripService;

    @Autowired
    private  TypeCarService typeCarService;

    @Autowired
    private  UserDriverTripRepository userDriverTripRepository;

    private void AddDriverForDriverTrip(String[] drivers, DriverTrip driverTrip){
        for(String driver: drivers){
            User user = userService.findByPhone(driver);
            if(user == null){
                return;
            }
            userDriverTripService.saveUserDriverTrip(user,driverTrip,driverTrip.getStatus(),driverTrip.getDate());
        }
    }

    @Override
    public  Boolean checkExistDriverFordate(String[] drivers, Date date){
        for (String phone: drivers){
            if(phone.equals("444444444")){
                continue;
            }
            User user = userService.findByPhone(phone);
            if(user == null){
                return false;
            }
            if(userDriverTripService.checkExitsDriverForDate(user,date)){
                return false;
            }
        }
        return true;
    }

    @Override
    public DriverTrip addOneTrip(DriverTripRequest driverTripRequest) {
        String[] listDriver = driverTripRequest.getDrivers().split("-");

        DriverTrip driverTrip = mapper.map(driverTripRequest,DriverTrip.class) ;
        Trip trip = tripService.getTripById(driverTripRequest.getIdtrip());
        Car car = carService.getCarById(driverTripRequest.getIdcar());

        driverTrip.setCar(car);
        driverTrip.setTrip(trip);

        if(driverTripRepository.existsByCarAndDate(car,driverTripRequest.getDate())){
            return  null;
        }

        if(checkExistDriverFordate(listDriver,driverTripRequest.getDate()) == false){
            return  null;
        }

        DriverTrip driverTrip1 = driverTripRepository.save(driverTrip);
        this.AddDriverForDriverTrip(listDriver,driverTrip1);

        return this.getOne(driverTrip1.getId());
    }

    @Override
    public DriverTrip getOne(Long id) {
        Optional<DriverTrip> optionalDriverTrip = driverTripRepository.findById(id);
        if(optionalDriverTrip.isPresent()){
            return  optionalDriverTrip.get();
        }
        return  null;
    }

    @Override
    @Transactional
    public Boolean updateStatus(Long id) {
        userDriverTripRepository.deleteByDriverTripId(id);
        driverTripRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public DriverTrip  editOneTrip(Long id, DriverTripRequest driverTripRequest) {
        String[] listDriver = driverTripRequest.getDrivers().split("-");

        driverTripRequest.setId(id);
        Optional<DriverTrip> optionalDriverTrip = driverTripRepository.findById(id);

        if(optionalDriverTrip.isPresent()){
            DriverTrip driverTrip = optionalDriverTrip.get();

            driverTrip.setStatus(driverTripRequest.getStatus());
            driverTrip.setDate(driverTripRequest.getDate());


            Car car = carService.getCarById(driverTripRequest.getIdcar());
            Trip trip = tripService.getTripById(driverTripRequest.getIdtrip());

            driverTrip.setCar(null);
            userDriverTripRepository.deleteByDriverTripId(driverTrip.getId());

            DriverTrip driverTrip1_ = driverTripRepository.save(driverTrip);

            if(driverTripRepository.existsByCarAndDateAndDifferentId(car,driverTripRequest.getDate(),id)){
                return  null;
            }

            if(checkExistDriverFordate(listDriver,driverTripRequest.getDate()) == false){
                return  null;
            }

            driverTrip1_.setTrip(trip);
            driverTrip1_.setCar(car);
            DriverTrip driverTrip1 = driverTripRepository.save(driverTrip1_);
            this.AddDriverForDriverTrip(listDriver,driverTrip1);

            return this.getOne(driverTrip1.getId());
        }
        return null;
    }

    private List<User> getDrivers(String[] listdrivers){
        List<User> list = new ArrayList<>();
        for(String s : listdrivers){
            User user = userService.findByPhone(s);
            list.add(user);
        }
        return list;
    }

    private  Boolean checkNgayDiChauyen(DriverTripRequest driverTripRequest){
        Date currentDate = new Date();
        Date dateToCheck = driverTripRequest.getDate();
        if (dateToCheck.before(currentDate) || dateToCheck.equals(currentDate)) {
            return  false;
        }
        return  true;
    }

    @Override
    public List<DriverTrip> addList(List<DriverTripRequest> list_) {
        for(DriverTripRequest driverTripRequest : list_){
            if(!checkNgayDiChauyen(driverTripRequest)){
                return null;
            }
        }
        List<DriverTrip> listsaved = new ArrayList<>();
        for(DriverTripRequest driverTripRequest : list_){
            String[] listDriver = driverTripRequest.getDrivers().split("-");

            DriverTrip driverTrip = mapper.map(driverTripRequest, DriverTrip.class);
            Trip trip = tripService.getTripById(driverTripRequest.getIdtrip());
            Car car = carService.getCarById(driverTripRequest.getIdcar());


            driverTrip.setTrip(trip);
            driverTrip.setCar(car);

            if(driverTripRepository.existsByCarAndDate(car,driverTripRequest.getDate())){
                return  null;
            }

            if(checkExistDriverFordate(listDriver,driverTripRequest.getDate()) == false){
                return  null;
            }

            DriverTrip driverTrip1 = driverTripRepository.save(driverTrip);
            this.AddDriverForDriverTrip(listDriver,driverTrip1);
            listsaved.add(getOne(driverTrip1.getId())) ;
        }
        return listsaved;
    }


    public Boolean checkDate(Date date){
        Date currentDate = new Date();
        if (date.after(currentDate) || date.equals(currentDate)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<DriverTrip> findByDateGreaterThanAndTrip(Date date, Long idTrip) {
        Trip trip = tripService.getTripById(idTrip);
        if(trip != null){
            return driverTripRepository.findByTripAndDate(trip,date);
        }
        return null;
    }

    @Override
    public List<DriverTrip> findByTripAndate(Long idTrip) {
        Trip trip = tripService.getTripById(idTrip);
        if(trip != null){
            return driverTripRepository.findByTrip(trip);
        }
        return null;
    }

    @Override
    public DriverTrip addOrUpdateCarDriverTripCar(String idCar, Long idDriverTrip) {
        Car car = carService.getCarById(idCar);
        DriverTrip driverTrip = getOne(idDriverTrip);
        if(car != null && driverTrip != null){
            driverTrip.setCar(car);
            return  driverTripRepository.save(driverTrip);
        }
        return null;
    }

    @Override
    public DriverTrip addListDriverForDriverTrip(AddListDriverForTripRequest addListDriverForTripRequest) {
        List<String> phoneDrivers = addListDriverForTripRequest.getPhoneDrivers();
        Long id = addListDriverForTripRequest.getIdtrip();
        DriverTrip driverTrip = getOne(id);
        List<User> list = new ArrayList<>();
        String[] list_ = new String[phoneDrivers.size()];
        for (int i = 0; i < phoneDrivers.size(); i++) {
            list_[i] = phoneDrivers.get(0);
        }
        this.AddDriverForDriverTrip(list_,driverTrip);
        return this.getOne(id);
    }

    @Override
    public void removeDriver(String phoneDriver, Long id) {
        User user = userService.findByPhone(phoneDriver);
        DriverTrip driverTrip = getOne(id);
        if(driverTrip != null && user != null){
            userDriverTripService.removeUserDriverTripByUserAndDriver(user,driverTrip);
        }

    }

    @Override
    public void deleteSeat(Long id) {
        driverTripRepository.deleteDriverTripSeatBySeatId(id);
    }

    @Override
    public List<DriverTrip> addListBetweenDay(String phoneCompany, Long idTrip, String start, String end, Long typecar) {
        Trip trip = tripService.getTripById(idTrip);
        TypeCar typeCar = typeCarService.GetTypecar(typecar);
        if(trip == null || typeCar == null){
            return null;
        }
        List<Car> cars  = carService.getAllByTypeAndCompany(typecar,phoneCompany);

        String[] startDay = start.split("/");
        String[] endDay =  end.split("/");

        LocalDate startDate = LocalDate.of(Integer.parseInt(startDay[2]), Integer.parseInt(startDay[0]), Integer.parseInt(startDay[1]));
        LocalDate endDate = LocalDate.of(Integer.parseInt(endDay[2]), Integer.parseInt(endDay[0]), Integer.parseInt(endDay[1]));

        LocalDate currentDate = startDate;
        List<DriverTrip> list = new ArrayList<>();
        while (!currentDate.isAfter(endDate)) {
            Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            DriverTrip driverTrip = new DriverTrip();
            driverTrip.setTrip(trip);
            driverTrip.setDate(date);
            driverTrip.setStatus(true);

            Car carTemp = null;
            for (Car car : cars){
                if(driverTripRepository.existsByCarAndDate(car,date)) {
                    continue;
                }else {
                    carTemp = car;
                    break;
                }
            }
            if(carTemp == null){
                continue;
            }
            driverTrip.setCar(carTemp);


            DriverTrip driverTrip1 = driverTripRepository.save(driverTrip);
            String[] listDriver = {"444444444"};
            this.AddDriverForDriverTrip(listDriver,driverTrip1);
            list.add(getOne(driverTrip1.getId())) ;

            currentDate = currentDate.plusDays(1);
        }

        return list;
    }


}
