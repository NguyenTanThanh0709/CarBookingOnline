package com.carbooking.carbookingonlineserver.service.implservice;

import com.carbooking.carbookingonlineserver.entity.Contact;
import com.carbooking.carbookingonlineserver.repository.ContactRepository;
import com.carbooking.carbookingonlineserver.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class implContact implements ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Override
    public Contact AddContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact getContactByid(Long id) {
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isPresent()){
            return contact.get();
        }
        return  null;
    }

    @Override
    public List<Contact> getAllContact() {
        return contactRepository.findAll();
    }
}
