package com.carbooking.carbookingonlineserver.service;

import com.carbooking.carbookingonlineserver.dto.repuest.TripRequest;
import com.carbooking.carbookingonlineserver.entity.Trip;

import java.util.List;

public interface TripService {
    Trip addTrip(TripRequest trip);
    List<Trip> addListTrip(List<TripRequest> tripRequests);
    Boolean updateStatusTrip(Long idTrip);
    Trip updateTrip(Long idTrip,TripRequest trip);
    List<Trip> listTripOfCompany(String phoneCompany);
    Trip getTripById(Long id);
    List<Trip> getTripByPickUpAndDropOff(String pickup, String DropOff);
    Trip updateTripImageUrl(Long tripId, String newImageUrl);
    void UpdatePromotions(Long idTrip, Long idPromotios);


}
