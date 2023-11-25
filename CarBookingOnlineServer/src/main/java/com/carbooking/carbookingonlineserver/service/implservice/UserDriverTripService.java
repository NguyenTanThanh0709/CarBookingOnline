package com.carbooking.carbookingonlineserver.service.implservice;

import com.carbooking.carbookingonlineserver.dto.reponse.DriverTripReponDriver;
import com.carbooking.carbookingonlineserver.entity.DriverTrip;
import com.carbooking.carbookingonlineserver.entity.User;
import com.carbooking.carbookingonlineserver.entity.UserDriverTrip;
import com.carbooking.carbookingonlineserver.entity.UserDriverTripId;
import com.carbooking.carbookingonlineserver.repository.UserDriverTripRepository;
import com.carbooking.carbookingonlineserver.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserDriverTripService {
    @Autowired
    private UserDriverTripRepository userDriverTripRepository;

    @Autowired
    private UserService userService;

    public void removeUserDriverTripByUserAndDriver(User user, DriverTrip driverTrip) {
        userDriverTripRepository.deleteByUserAndDriverTrip(user, driverTrip);
    }

    @Transactional
    public void saveUserDriverTrip(User user, DriverTrip driverTrip, Boolean status, Date date) {
        UserDriverTrip userDriverTrip = new UserDriverTrip();
        UserDriverTripId userDriverTripId = new UserDriverTripId();

        userDriverTripId.setUserId(user.getId()); // Set the user's ID
        userDriverTripId.setDriverTripId(driverTrip.getId()); // Set the driver trip's ID

        userDriverTrip.setId(userDriverTripId); // Set the composite key
        userDriverTrip.setUser(user);
        userDriverTrip.setDriverTrip(driverTrip);
        userDriverTrip.setStatus(status);
        userDriverTrip.setDate(date);

        userDriverTripRepository.save(userDriverTrip);
    }

    public Boolean checkExitsDriverForDate(User user, Date date){
        return userDriverTripRepository.existsByUserAndDate(user,date);
    }

    public List<User> findUserDriverTripsByDriverTrip(DriverTrip driverTrip) {
        List<UserDriverTrip> list = userDriverTripRepository.findByDriverTrip(driverTrip);
        List<User> list_ = new ArrayList<>();
        for (UserDriverTrip userDriverTrip : list){
            list_.add(userDriverTrip.getUser());
        }
        return list_;
    }


    public List<DriverTripReponDriver> getDriverTripsByUserPhone(String phone) {
        List<DriverTripReponDriver> list = new ArrayList<>();
        User user = userService.findByPhone(phone);

        if (user != null) {
            List<UserDriverTrip> userDriverTrips = userDriverTripRepository.findByUser(user);

            for (UserDriverTrip userDriverTrip : userDriverTrips) {
                if (userDriverTrip.getDriverTrip() != null) {
                    DriverTripReponDriver driverTripReponDriver = new DriverTripReponDriver(
                            userDriverTrip.getDriverTrip().getId(),
                            userDriverTrip.getDriverTrip().getDate(),
                            userDriverTrip.getDriverTrip().getStatus(),
                            userDriverTrip.getDriverTrip().getTrip().getPickupLocation(),
                            userDriverTrip.getDriverTrip().getTrip().getDropoffLocation(),
                            userDriverTrip.getDriverTrip().getTrip().getPickupTime(),
                            userDriverTrip.getDriverTrip().getTrip().getDropoffTime(),
                            userDriverTrip.getDriverTrip().getCar().getLicenseplates(),
                            userDriverTrip.getDriverTrip().getCar().getTypeCar().getName(),
                            userDriverTrip.getDriverTrip().getSeats()
                    );
                    list.add(driverTripReponDriver);
                }
            }
        }

        return list;
    }


    @Transactional
    public void updateStatusById(UserDriverTripId userDriverTripId, Boolean newStatus) {
        userDriverTripRepository.updateStatusById(userDriverTripId, newStatus);
    }
}
