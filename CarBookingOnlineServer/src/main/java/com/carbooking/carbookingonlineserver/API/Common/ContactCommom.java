package com.carbooking.carbookingonlineserver.API.Common;


import com.carbooking.carbookingonlineserver.entity.Company;
import com.carbooking.carbookingonlineserver.entity.Contact;
import com.carbooking.carbookingonlineserver.service.CompanyService;
import com.carbooking.carbookingonlineserver.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0/common")
@CrossOrigin(origins = "http://localhost:3000")
public class ContactCommom {

    @Autowired
    private ContactService contactService;
    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies/user/{phone}")
    public ResponseEntity<Company> getCompanyByStaff(@PathVariable String phone) {
        Company company = companyService.getCompanyByStaff(phone);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/contacts")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        Contact contact1 = contactService.AddContact(contact);
        return new ResponseEntity<>(contact1, HttpStatus.OK);
    }
}
    