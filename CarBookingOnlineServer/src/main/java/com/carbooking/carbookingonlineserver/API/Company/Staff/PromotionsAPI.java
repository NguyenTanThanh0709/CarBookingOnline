package com.carbooking.carbookingonlineserver.API.Company.Staff;

import com.carbooking.carbookingonlineserver.dto.repuest.PromotionsRequest;
import com.carbooking.carbookingonlineserver.entity.Promotions;
import com.carbooking.carbookingonlineserver.service.PromotionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/staff")
@CrossOrigin(origins = "http://localhost:3000")
public class PromotionsAPI {
    @Autowired
    private PromotionsService promotionsService;

    @PostMapping("/promotion")
    public ResponseEntity<Promotions> createPromotion(@RequestBody PromotionsRequest promotionsRequest) {
        Promotions promotion = promotionsService.addOne(promotionsRequest);
        if (promotion != null) {
            return new ResponseEntity<>(promotion, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/promotion/{id}")
    public ResponseEntity<Promotions> getPromotion(@PathVariable Long id) {
        Promotions promotion = promotionsService.getOne(id);
        if (promotion != null) {
            return new ResponseEntity<>(promotion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Promotions> updatePromotion(@PathVariable Long id, @RequestBody PromotionsRequest promotionsRequest) {
        Promotions updatedPromotion = promotionsService.editOne(id, promotionsRequest);
        if (updatedPromotion != null) {
            return new ResponseEntity<>(updatedPromotion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePromotionStatus(@PathVariable Long id, @RequestBody Boolean status) {
        boolean updated = promotionsService.updateStatus(id, status);
        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/promotion/company/{phoneCompany}")
    public ResponseEntity<?> getAllPromotionsForCompany(@PathVariable String phoneCompany) {
        List<Promotions> promotions = promotionsService.getAllOffComapny(phoneCompany);
        if(promotions != null){
            return new ResponseEntity<>(promotions, HttpStatus.OK);
        }
        String Message = "Không tìm thấy!";
        return new ResponseEntity<>(Message, HttpStatus.NOT_FOUND);
    }

}
