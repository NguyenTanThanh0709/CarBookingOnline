package com.carbooking.carbookingonlineserver.repository;

import com.carbooking.carbookingonlineserver.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
}
