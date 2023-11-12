package com.carbooking.carbookingonlineserver.service;

import com.carbooking.carbookingonlineserver.dto.repuest.LocateDetailRequest;
import com.carbooking.carbookingonlineserver.entity.DetailPickUpLocation;

import java.util.List;

public interface DetailPickUpLocationService {
    List<DetailPickUpLocation> addListLocate(List<LocateDetailRequest> list);
    DetailPickUpLocation editLocate(Long id, LocateDetailRequest locateDetailRequest);
    List<DetailPickUpLocation> getallOftrip(Long idtrip);
    DetailPickUpLocation getDetailByil(Long id);
    boolean delete(Long id);
    DetailPickUpLocation addOne(LocateDetailRequest locateDetailRequest);
}
