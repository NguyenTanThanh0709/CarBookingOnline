package com.carbooking.carbookingonlineserver.service;

import com.carbooking.carbookingonlineserver.dto.repuest.AddListDriverForTripRequest;
import com.carbooking.carbookingonlineserver.dto.repuest.DriverTripRequest;
import com.carbooking.carbookingonlineserver.entity.DriverTrip;
import com.carbooking.carbookingonlineserver.entity.User;

import java.sql.Driver;
import java.util.Date;
import java.util.List;

public interface DriverTripService {
    DriverTrip addOneTrip(DriverTripRequest driverTripRequest);
    DriverTrip getOne(Long id);
    Boolean checkDate(Date date);
    Boolean checkExistDriverFordate(String[] drivers, Date date);
    Boolean updateStatus(Long id);
    DriverTrip editOneTrip(Long id, DriverTripRequest driverTripRequest);
    List<DriverTrip> addList(List<DriverTripRequest> list);
    List<DriverTrip> findByDateGreaterThanAndTrip(Date date, Long idTrip);
    List<DriverTrip> findByTripAndate(Long idTrip);
    DriverTrip addOrUpdateCarDriverTripCar(String idCar, Long idDriverTrip);
    DriverTrip addListDriverForDriverTrip(AddListDriverForTripRequest addListDriverForTripRequest);
    void removeDriver(String phoneDriver, Long id);

    void deleteSeat(Long id);


}
