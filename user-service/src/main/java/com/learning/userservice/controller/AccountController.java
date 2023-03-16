package com.learning.userservice.controller;

import com.learning.userservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/account")
public class AccountController {

    @Autowired
    PersonService personService;

    @PostMapping(value = "/updateAccountAddress")
    public ResponseEntity<?> updateAddressCity(@RequestParam("personId") String personId, @RequestParam("city") String city) {

        return new ResponseEntity<>(personService.updateCityAddressDaoService(personId, city), HttpStatus.OK);

    }

    @GetMapping(value = "/getAllAccounts")
    public ResponseEntity<?> getAllPersonWithAddress() {
        return new ResponseEntity<>(personService.getAllPersonWithAddressService(), HttpStatus.OK);
    }
}
