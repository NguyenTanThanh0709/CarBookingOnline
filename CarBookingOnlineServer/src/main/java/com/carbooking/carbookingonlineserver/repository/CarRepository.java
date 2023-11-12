package com.carbooking.carbookingonlineserver.repository;

import com.carbooking.carbookingonlineserver.entity.Car;
import com.carbooking.carbookingonlineserver.entity.Company;
import com.carbooking.carbookingonlineserver.entity.TypeCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,String> {
    List<Car> findByPhoneCompany(Company company);
    boolean existsByLicenseplates(String licenseplates);
    Optional<Car> findByLicenseplates(String id);
    List<Car> findByTypeCarAndPhoneCompany(TypeCar typeCar,Company company);
    @Modifying
    @Query("UPDATE Car c SET c.availability = :availability WHERE c.id = :carId")
    void setAvailabilityById(@Param("carId") String carId, @Param("availability") Boolean availability);
}
