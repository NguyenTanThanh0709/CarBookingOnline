package com.carbooking.carbookingonlineserver.repository;

import com.carbooking.carbookingonlineserver.entity.DetailDropOffLocation;
import com.carbooking.carbookingonlineserver.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailDropOffLocationRepository extends JpaRepository<DetailDropOffLocation,Long> {
    List<DetailDropOffLocation> findByTrip(Trip trip);
}
