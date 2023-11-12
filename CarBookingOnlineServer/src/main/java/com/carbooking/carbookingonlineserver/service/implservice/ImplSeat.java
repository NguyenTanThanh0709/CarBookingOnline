package com.carbooking.carbookingonlineserver.service.implservice;

import com.carbooking.carbookingonlineserver.entity.Car;
import com.carbooking.carbookingonlineserver.entity.Seat;
import com.carbooking.carbookingonlineserver.repository.SeatRepository;
import com.carbooking.carbookingonlineserver.service.CarService;
import com.carbooking.carbookingonlineserver.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImplSeat implements SeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Override
    public List<Seat> listSeatOfCar(Car car) {
        if(car == null){
            return new ArrayList<>();
        }
        return seatRepository.findByCar(car);
    }


    @Override
    public Seat getByid(Long id) {
        Optional<Seat> seatOptional =seatRepository.findById(id);
        if (seatOptional.isPresent()){
            return seatOptional.get();
        }
        return  null;
    }

    @Override
    public List<Seat> insertListSeatForCar(Car car) {
        int countOfSeat = car.getTypeCar().getNumberOfSeats();
        List<Seat> list = new ArrayList<>();
        for(int i = 1; i <= countOfSeat; i++){
            Seat seat = new Seat();
            seat.setName("Seat_"+i);
            seat.setCar(car);
            list.add(seat);
        }
        return seatRepository.saveAllAndFlush(list);
    }


}
