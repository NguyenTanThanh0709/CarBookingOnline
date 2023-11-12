package com.carbooking.carbookingonlineserver.repository;

import com.carbooking.carbookingonlineserver.dto.Role;
import com.carbooking.carbookingonlineserver.entity.Company;
import com.carbooking.carbookingonlineserver.entity.DriverTrip;
import com.carbooking.carbookingonlineserver.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Boolean existsByEmail(String email);
    Boolean existsByPhone(String phone);
    Optional<User> findByPhone(String phone);
    Optional<User> findByLicenseNumber(String licenseNumber);
    List<User> findByCompany( Company company);
    List<User> findByRoleAndCompany(Role role, Company company);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.password = ?1 WHERE u.phone = ?2")
    void updatePasswordByPhone(String password, String phone);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.name = ?1, u.email = ?2 WHERE u.phone = ?3")
    void updateNameAndEmailByPhone(String name, String email, String phone);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.status = :newStatus WHERE u.role = 'DRIVER' AND u.phone = :phone")
    void updateStatusForDriversByPhone(@Param("newStatus") Boolean newStatus, @Param("phone") String phone);



}
