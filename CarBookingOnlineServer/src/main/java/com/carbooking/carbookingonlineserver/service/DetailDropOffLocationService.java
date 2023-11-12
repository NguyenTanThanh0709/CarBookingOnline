package com.carbooking.carbookingonlineserver.service;

import com.carbooking.carbookingonlineserver.dto.repuest.LocateDetailRequest;
import com.carbooking.carbookingonlineserver.entity.DetailDropOffLocation;


import java.util.List;

public interface DetailDropOffLocationService {
    List<DetailDropOffLocation> addListLocate(List<LocateDetailRequest> list);
    DetailDropOffLocation editLocate(Long id, LocateDetailRequest locateDetailRequest);
    List<DetailDropOffLocation> getallOftrip(Long idtrip);
    DetailDropOffLocation getDetailByil(Long id);
    boolean delete(Long id);
    DetailDropOffLocation addOne(LocateDetailRequest locateDetailRequest);
}
