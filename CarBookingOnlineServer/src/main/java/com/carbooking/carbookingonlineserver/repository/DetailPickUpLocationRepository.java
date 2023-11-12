package com.carbooking.carbookingonlineserver.repository;

import com.carbooking.carbookingonlineserver.entity.DetailDropOffLocation;
import com.carbooking.carbookingonlineserver.entity.DetailPickUpLocation;
import com.carbooking.carbookingonlineserver.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailPickUpLocationRepository extends JpaRepository<DetailPickUpLocation,Long> {
    List<DetailPickUpLocation> findByTrip(Trip trip);
}
