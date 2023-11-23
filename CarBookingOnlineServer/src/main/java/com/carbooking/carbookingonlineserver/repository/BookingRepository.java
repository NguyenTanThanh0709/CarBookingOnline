package com.carbooking.carbookingonlineserver.repository;

import com.carbooking.carbookingonlineserver.entity.Booking;
import com.carbooking.carbookingonlineserver.entity.DriverTrip;
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
public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByDriverTrip(DriverTrip driverTrip);
    List<Booking> findByUser(User user);
    List<Booking> findByUserAndDateAfter(User user, Date date);

    @Transactional
    @Modifying
    @Query("UPDATE Booking b SET b.status = :status WHERE b.id = :id")
    void updateStatusById(@Param("id") Long id, @Param("status") String status);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM booking_seat WHERE booking_id = :bookingId", nativeQuery = true)
    void deleteBookingSeatByBookingId(@Param("bookingId") Long bookingId);
}
