package com.carbooking.carbookingonlineserver.repository;

import com.carbooking.carbookingonlineserver.entity.Company;
import com.carbooking.carbookingonlineserver.entity.Promotions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PromotionsRepository extends JpaRepository<Promotions,Long> {
    List<Promotions> findByCompany(Company company);
    Optional<Promotions> findByCode(String code);
}
