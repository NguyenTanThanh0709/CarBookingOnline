package com.carbooking.carbookingonlineserver.service;

import com.carbooking.carbookingonlineserver.entity.Contact;

import java.util.List;

public interface ContactService {
    Contact AddContact(Contact contact);
    Contact getContactByid(Long id);
    List<Contact> getAllContact();
}
