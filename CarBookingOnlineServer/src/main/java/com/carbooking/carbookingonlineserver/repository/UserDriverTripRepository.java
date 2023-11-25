package com.carbooking.carbookingonlineserver.repository;


import com.carbooking.carbookingonlineserver.entity.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface UserDriverTripRepository extends JpaRepository<UserDriverTrip, UserDriverTripId> {
    @Transactional
    void deleteByUserAndDriverTrip(User user, DriverTrip driverTrip);
    boolean existsByUserAndDate(User user, Date date);

    @Modifying
    @Query("DELETE FROM UserDriverTrip udt WHERE udt.driverTrip.id = :driverTripId")
    void deleteByDriverTripId(@Param("driverTripId") Long driverTripId);

    List<UserDriverTrip> findByUser(User user);

    @Transactional
    List<UserDriverTrip> findByDriverTrip(DriverTrip driverTrip);
    @Transactional
    @Modifying
    @Query("UPDATE UserDriverTrip u " +
            "SET u.status = :newStatus " +
            "WHERE u.id = :userDriverTripId")
    void updateStatusById(@Param("userDriverTripId") UserDriverTripId userDriverTripId, @Param("newStatus") Boolean newStatus);
}
