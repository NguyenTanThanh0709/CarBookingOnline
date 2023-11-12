package com.carbooking.carbookingonlineserver.API.Admin;

import com.carbooking.carbookingonlineserver.entity.Company;
import com.carbooking.carbookingonlineserver.entity.Contact;
import com.carbooking.carbookingonlineserver.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v2/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class ContactAPI {
    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts/{id}")
    public ResponseEntity<Contact> getContactaByid(@PathVariable Long id) {
        Contact contact = contactService.getContactByid(id);
        if (contact != null) {
            return new ResponseEntity<>(contact, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>> getAllContact(){
        List<Contact> list = new ArrayList<>();
        list = contactService.getAllContact();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }




}
