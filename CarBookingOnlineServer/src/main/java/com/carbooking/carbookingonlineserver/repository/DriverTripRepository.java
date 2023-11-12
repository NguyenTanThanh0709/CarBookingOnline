package com.carbooking.carbookingonlineserver.repository;

import com.carbooking.carbookingonlineserver.entity.Car;
import com.carbooking.carbookingonlineserver.entity.DriverTrip;
import com.carbooking.carbookingonlineserver.entity.Trip;
import com.carbooking.carbookingonlineserver.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DriverTripRepository extends JpaRepository<DriverTrip,Long> {
    @Modifying
    @Query("UPDATE DriverTrip c SET c.status = :status WHERE c.id = :id")
    void setStatusById(@Param("id") Long id, @Param("status") Boolean status);
    List<DriverTrip> findByTripAndDate(Trip trip, Date date);
    List<DriverTrip> findByTrip(Trip trip);
    List<DriverTrip> findByDate(Date date);
    List<DriverTrip> findByDateGreaterThanEqual(Date date);

    @Query("SELECT dt FROM DriverTrip dt WHERE dt.trip = :trip AND dt.date >= :date")
    List<DriverTrip> findDriverTripsByTripAndDateAfter(@Param("trip") Trip trip, @Param("date") Date date);
    boolean existsByCarAndDate(Car car, Date date);
    @Query("SELECT COUNT(dt) > 0 FROM DriverTrip dt " +
            "WHERE dt.car = :car " +
            "AND dt.date = :date " +
            "AND dt.id != :id")
    boolean existsByCarAndDateAndDifferentId(@Param("car") Car car,
                                             @Param("date") Date date,
                                             @Param("id") Long id);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM driver_trip_seat WHERE seat_id = :seatId", nativeQuery = true)
    void deleteDriverTripSeatBySeatId(@Param("seatId") Long seatId);
}
