package com.carbooking.carbookingonlineserver.service;

import com.carbooking.carbookingonlineserver.dto.repuest.PromotionsRequest;
import com.carbooking.carbookingonlineserver.entity.Promotions;

import java.util.List;

public interface PromotionsService {
    Promotions addOne(PromotionsRequest promotionsRequest);
    Promotions editOne(Long id, PromotionsRequest promotionsRequest);
    Boolean updateStatus(Long id, Boolean status);
    List<Promotions> getAllOffComapny(String PhoneCompany);
    Promotions getOne(Long id);
}
