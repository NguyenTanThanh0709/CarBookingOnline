package com.carbooking.carbookingonlineserver.service.implservice;

import com.carbooking.carbookingonlineserver.dto.repuest.TripRequest;
import com.carbooking.carbookingonlineserver.entity.Company;
import com.carbooking.carbookingonlineserver.entity.DetailDropOffLocation;
import com.carbooking.carbookingonlineserver.entity.Promotions;
import com.carbooking.carbookingonlineserver.entity.Trip;
import com.carbooking.carbookingonlineserver.repository.TripRepository;
import com.carbooking.carbookingonlineserver.service.CompanyService;
import com.carbooking.carbookingonlineserver.service.PromotionsService;
import com.carbooking.carbookingonlineserver.service.TripService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class implTrip implements TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PromotionsService promotionsService;

    @Override
    public Trip addTrip(TripRequest trip) {
        Trip trip1 = mapper.map(trip, Trip.class);
        trip1.setUrlimage("https://storage.dx.gov.vn/Data/c_0/2022/10/07/1-638007365766931686.jpg");
        return tripRepository.save(trip1);
    }

    @Override
    public List<Trip> addListTrip(List<TripRequest> tripRequests) {
        List<Trip> list = new ArrayList<>();
        for (TripRequest tripRequest: tripRequests){
            Trip trip1 = mapper.map(tripRequest, Trip.class);
            trip1.setPhoneCompany(companyService.getCompany(tripRequest.getPhoneCompany()));
            trip1.setUrlimage("https://storage.dx.gov.vn/Data/c_0/2022/10/07/1-638007365766931686.jpg");
            list.add(trip1);
        }
        return tripRepository.saveAll(list);
    }

    @Override
    @Transactional
    public Boolean updateStatusTrip(Long idTrip) {
        Optional<Trip> optionalTrip = tripRepository.findById(idTrip);
        Boolean check = null;
        if(optionalTrip.isPresent()){
            Trip trip = optionalTrip.get();
            check = !trip.getStatus();
            tripRepository.updateStatus(idTrip,check);
        }
        return check;
    }

    @Override
    public Trip updateTrip(Long idTrip, TripRequest trip) {
        trip.setId(idTrip);
        Optional<Trip> optionalTrip = tripRepository.findById(idTrip);
        if(optionalTrip.isPresent()){
            Trip trip1 = optionalTrip.get();
            mapper.map(trip,trip1);
            return  tripRepository.save(trip1);
        }
        return null;
    }

    @Override
    public List<Trip> listTripOfCompany(String phoneCompany) {
        Company company = companyService.getCompany(phoneCompany);
        if(company != null){
            return tripRepository.findByPhoneCompany(company);
        }
        return null;
    }

    @Override
    public Trip getTripById(Long id) {
        Optional<Trip> optionalTrip = tripRepository.findById(id);
        if(optionalTrip.isPresent()){
            return optionalTrip.get();
        }
        return null;
    }

    @Override
    public List<Trip> getTripByPickUpAndDropOff(String pickup, String DropOff) {
        return tripRepository.findByPickupLocationContainingAndDropoffLocationContaining(pickup,DropOff);
    }

    @Override
    public Trip updateTripImageUrl(Long tripId, String newImageUrl) {
        Optional<Trip> trip1 = tripRepository.findById(tripId);
        if(!trip1.isPresent()){
            return null;
        }
        Trip trip = trip1.get();
        trip.setUrlimage(newImageUrl);
        return tripRepository.save(trip);
    }

    @Override
    public void UpdatePromotions(Long idTrip, Long idPromotios) {
        Promotions optionalPromotions = promotionsService.getOne(idPromotios);
        if(idPromotios ==null || idPromotios == -1){
            tripRepository.removePromotionById(idTrip);
        }
        if(optionalPromotions != null){
            tripRepository.updatePromotionById(idTrip,optionalPromotions);
        }
    }
}
