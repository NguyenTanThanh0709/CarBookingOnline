package com.carbooking.carbookingonlineserver.service;

import com.carbooking.carbookingonlineserver.entity.Car;
import com.carbooking.carbookingonlineserver.entity.Seat;

import java.util.List;

public interface SeatService {
    List<Seat> listSeatOfCar(Car car);
    List<Seat> insertListSeatForCar(Car car);
    Seat getByid(Long id);
}
