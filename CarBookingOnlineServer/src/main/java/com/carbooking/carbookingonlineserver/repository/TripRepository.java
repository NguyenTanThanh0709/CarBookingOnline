package com.carbooking.carbookingonlineserver.repository;

import com.carbooking.carbookingonlineserver.entity.Company;
import com.carbooking.carbookingonlineserver.entity.Promotions;
import com.carbooking.carbookingonlineserver.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip,Long> {
    List<Trip> findByPickupLocationContainingAndDropoffLocationContaining(String pickupLocation, String dropoffLocation);
    List<Trip> findByPhoneCompany(Company company);
    @Modifying
    @Query("UPDATE Trip t SET t.promotion = :promotion WHERE t.id = :tripId")
    void updatePromotionById(@Param("tripId") Long tripId, @Param("promotion") Promotions promotion);

    @Modifying
    @Query("UPDATE Trip t SET t.promotion = null WHERE t.id = :tripId")
    void removePromotionById(@Param("tripId") Long tripId);
    @Modifying
    @Query("UPDATE Trip t SET t.status = :newStatus WHERE t.id = :tripId")
    void updateStatus(@Param("tripId") Long tripId, @Param("newStatus") Boolean newStatus);
}
