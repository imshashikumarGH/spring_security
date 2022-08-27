package com.learning.userservice.controller;

import com.learning.userservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;


@RestController()
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping(value = "/updateAddress")
    public ResponseEntity<?> updateAddressCity(@PathParam("personId") String personId, @PathParam("city") String city) {

        return new ResponseEntity<>(personService.updateCityAddressDaoService(personId, city), HttpStatus.OK);

    }

    @GetMapping(value = "/getAllPersonWithAddress")
    public ResponseEntity<?> getAllPersonWithAddress() {
        return new ResponseEntity<>(personService.getAllPersonWithAddressService(), HttpStatus.OK);
    }
}
