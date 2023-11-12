package com.carbooking.carbookingonlineserver.service.implservice;

import com.carbooking.carbookingonlineserver.dto.repuest.LocateDetailRequest;
import com.carbooking.carbookingonlineserver.entity.DetailDropOffLocation;
import com.carbooking.carbookingonlineserver.entity.DetailPickUpLocation;
import com.carbooking.carbookingonlineserver.entity.Trip;
import com.carbooking.carbookingonlineserver.repository.DetailDropOffLocationRepository;
import com.carbooking.carbookingonlineserver.service.DetailDropOffLocationService;
import com.carbooking.carbookingonlineserver.service.TripService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImplDetailDropOffLocation implements DetailDropOffLocationService {
    @Autowired
    private DetailDropOffLocationRepository detailDropOffLocationRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private TripService tripService;

    @Override
    public List<DetailDropOffLocation> addListLocate(List<LocateDetailRequest> list_) {
        List<DetailDropOffLocation> list = new ArrayList<>();
        for (LocateDetailRequest locateDetailRequest : list_){
            DetailDropOffLocation detailPickUpLocation = mapper.map(locateDetailRequest,DetailDropOffLocation.class);
            Trip trip = tripService.getTripById(locateDetailRequest.getIdtrip());
            if(trip == null){
                return  null;
            }

            detailPickUpLocation.setTrip(trip);
            detailPickUpLocation.setIdtrip(trip.getId());
            list.add(detailPickUpLocation);
        }
        return detailDropOffLocationRepository.saveAll(list);
    }

    @Override
    public DetailDropOffLocation editLocate(Long id, LocateDetailRequest locateDetailRequest) {
        locateDetailRequest.setId(id);
        Optional<DetailDropOffLocation> offLocation = detailDropOffLocationRepository.findById(id);
        if(offLocation.isPresent()){
            DetailDropOffLocation detailDropOffLocation = offLocation.get();
            mapper.map(locateDetailRequest,detailDropOffLocation);
            return detailDropOffLocationRepository.save(detailDropOffLocation);
        }
        return null;
    }

    @Override
    public List<DetailDropOffLocation> getallOftrip(Long idtrip) {
        Trip trip = tripService.getTripById(idtrip);
        if(trip != null){
            return detailDropOffLocationRepository.findByTrip(trip);
        }

        return null;
    }

    @Override
    public DetailDropOffLocation getDetailByil(Long id) {
        Optional<DetailDropOffLocation> optionalDetailDropOffLocation = detailDropOffLocationRepository.findById(id);
        if(optionalDetailDropOffLocation.isPresent()){
            return optionalDetailDropOffLocation.get();
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        detailDropOffLocationRepository.deleteById(id);
        return true;
    }

    @Override
    public DetailDropOffLocation addOne(LocateDetailRequest locateDetailRequest) {
        DetailDropOffLocation detailPickUpLocation = mapper.map(locateDetailRequest, DetailDropOffLocation.class);
        Trip trip = tripService.getTripById(locateDetailRequest.getIdtrip());
        if(trip != null){
            detailPickUpLocation.setTrip(trip);
            detailPickUpLocation.setIdtrip(trip.getId());
            return  detailDropOffLocationRepository.save(detailPickUpLocation);
        }
        return null;
    }
}
