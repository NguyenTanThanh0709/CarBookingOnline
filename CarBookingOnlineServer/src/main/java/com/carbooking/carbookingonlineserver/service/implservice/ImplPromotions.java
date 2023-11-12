package com.carbooking.carbookingonlineserver.service.implservice;

import com.carbooking.carbookingonlineserver.dto.repuest.PromotionsRequest;
import com.carbooking.carbookingonlineserver.entity.Company;
import com.carbooking.carbookingonlineserver.entity.Promotions;
import com.carbooking.carbookingonlineserver.repository.PromotionsRepository;
import com.carbooking.carbookingonlineserver.service.CompanyService;
import com.carbooking.carbookingonlineserver.service.PromotionsService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public Promotions addOne(PromotionsRequest promotionsRequest) {
        Promotions promotion = mapper.map(promotionsRequest, Promotions.class);

        Company company = companyService.getCompany(promotionsRequest.getPhoneCompany());
        if (company != null) {
            promotion.setCompany(company);
            return promotionsRepository.save(promotion);
        }
        return null;
    }

    @Override
    public Promotions editOne(Long id, PromotionsRequest promotionsRequest) {
        promotionsRequest.setId(id);
        Optional<Promotions> existingPromotion = promotionsRepository.findById(id);
        if (existingPromotion.isPresent()) {
            Promotions promotions = existingPromotion.get();
            // Chuyển đổi PromotionsRequest thành đối tượng Promotions và cập nhật trường id
            mapper.map(promotionsRequest,promotions);
            return promotionsRepository.save(promotions);
        }
        return null; // Xử lý khi không tìm thấy Promotion
    }

    @Override
    @Transactional
    public Boolean updateStatus(Long id, Boolean status) {
        Optional<Promotions> existingPromotion = promotionsRepository.findById(id);
        if (existingPromotion.isPresent()) {
            promotionsRepository.updateStatusById(id,status);
        }
        return null; // Xử lý khi không tìm thấy Promotion
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
