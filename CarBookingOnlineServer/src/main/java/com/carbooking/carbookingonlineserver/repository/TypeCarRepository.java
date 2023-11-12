package com.carbooking.carbookingonlineserver.repository;

import com.carbooking.carbookingonlineserver.entity.TypeCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeCarRepository extends JpaRepository<TypeCar,Long> {
}
