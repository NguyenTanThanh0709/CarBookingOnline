package com.carbooking.carbookingonlineserver.service.implservice;

import com.carbooking.carbookingonlineserver.dto.repuest.LocateDetailRequest;
import com.carbooking.carbookingonlineserver.entity.DetailPickUpLocation;
import com.carbooking.carbookingonlineserver.entity.Trip;
import com.carbooking.carbookingonlineserver.repository.DetailPickUpLocationRepository;
import com.carbooking.carbookingonlineserver.service.DetailPickUpLocationService;
import com.carbooking.carbookingonlineserver.service.TripService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImplDetailPickUpLocation implements DetailPickUpLocationService {
    @Autowired
    private DetailPickUpLocationRepository detailPickUpLocationRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private TripService tripService;
    @Override
    public List<DetailPickUpLocation> addListLocate(List<LocateDetailRequest> list_) {
        List<DetailPickUpLocation> list = new ArrayList<>();
        for (LocateDetailRequest locateDetailRequest : list_){
            DetailPickUpLocation detailPickUpLocation = mapper.map(locateDetailRequest,DetailPickUpLocation.class);
            Trip trip = tripService.getTripById(locateDetailRequest.getIdtrip());
            if(trip == null){
                return  null;
            }
            detailPickUpLocation.setTrip(trip);
            detailPickUpLocation.setIdtrip(trip.getId());
            list.add(detailPickUpLocation);
        }
        return detailPickUpLocationRepository.saveAll(list);
    }
    @Override
    public DetailPickUpLocation editLocate(Long id, LocateDetailRequest locateDetailRequest) {
        locateDetailRequest.setId(id);
        Optional<DetailPickUpLocation> offLocation = detailPickUpLocationRepository.findById(id);
        if(offLocation.isPresent()){
            DetailPickUpLocation detailDropOffLocation = offLocation.get();
            mapper.map(locateDetailRequest,detailDropOffLocation);
            return detailPickUpLocationRepository.save(detailDropOffLocation);
        }
        return null;
    }

    @Override
    public List<DetailPickUpLocation> getallOftrip(Long idtrip) {
        Trip trip = tripService.getTripById(idtrip);
        if(trip != null){
           return detailPickUpLocationRepository.findByTrip(trip);
        }

        return null;
    }

    @Override
    public DetailPickUpLocation getDetailByil(Long id) {
        Optional<DetailPickUpLocation> optionalDetailDropOffLocation = detailPickUpLocationRepository.findById(id);
        if(optionalDetailDropOffLocation.isPresent()){
            return optionalDetailDropOffLocation.get();
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        detailPickUpLocationRepository.deleteById(id);
        return true;
    }

    @Override
    public DetailPickUpLocation addOne(LocateDetailRequest locateDetailRequest) {
        DetailPickUpLocation detailPickUpLocation = mapper.map(locateDetailRequest, DetailPickUpLocation.class);
        Trip trip = tripService.getTripById(locateDetailRequest.getIdtrip());
        if(trip != null){
            detailPickUpLocation.setTrip(trip);
            detailPickUpLocation.setIdtrip(trip.getId());
            return  detailPickUpLocationRepository.save(detailPickUpLocation);
        }
        return null;
    }
}
