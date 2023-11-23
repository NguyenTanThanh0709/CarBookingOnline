package com.carbooking.carbookingonlineserver.service.implservice;

import com.carbooking.carbookingonlineserver.dto.repuest.PromotionsRequest;
import com.carbooking.carbookingonlineserver.entity.Company;
import com.carbooking.carbookingonlineserver.entity.Promotions;
import com.carbooking.carbookingonlineserver.entity.Trip;
import com.carbooking.carbookingonlineserver.repository.PromotionsRepository;
import com.carbooking.carbookingonlineserver.service.CompanyService;
import com.carbooking.carbookingonlineserver.service.PromotionsService;
import com.carbooking.carbookingonlineserver.service.TripService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImplPromotions implements PromotionsService {
    @Autowired
    private PromotionsRepository promotionsRepository;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TripService tripService;
    @Override
    public Promotions addOne(PromotionsRequest promotionsRequest) {
        Promotions promotion = mapper.map(promotionsRequest, Promotions.class);

        Company company = companyService.getCompany(promotionsRequest.getCompany());
        String[] listIdTrip = promotionsRequest.getTrips().split("-");
        List<Trip> tripList = new ArrayList<>();

        for (String id : listIdTrip) {
            Trip trip = tripService.getTripById(Long.parseLong(id));
            if (trip != null) {
                tripList.add(trip);
                trip.setPromotion(promotion); // Update the Trip entity with the associated Promotion
            }
        }

        promotion.setTrips(tripList);

        if (company != null) {
            promotion.setCompany(company);

            for (Trip trip : tripList) {
                trip.setPromotion(promotion); // Ensure bidirectional relationship is set
            }

            return promotionsRepository.save(promotion);
        }

        return null;
    }

    @Override
    public List<Promotions> getAllOffComapny(String PhoneCompany) {
        Company company = companyService.getCompany(PhoneCompany);
        if(company != null){
            return promotionsRepository.findByCompany(company);
        }
        return null;
    }

    @Override
    public Promotions getOne(Long id) {
        Optional<Promotions> promotion = promotionsRepository.findById(id);
        return promotion.orElse(null);
    }
}
