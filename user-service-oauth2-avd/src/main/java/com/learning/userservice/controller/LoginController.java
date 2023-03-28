package com.learning.userservice.controller;

import com.learning.userservice.exception.BadRequestException;
import com.learning.userservice.model.CustomerRequest;
import com.learning.userservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping(value = "/register")
    public ResponseEntity<Map> registerCustomer(@RequestBody CustomerRequest customerRequest) {
        if (customerRequest.getEmail().equals("")) {
            throw new BadRequestException("bad request");
        }
        loginService.registerUser(customerRequest);

        Map<String, String> map = new HashMap<>();
        map.put("Message", "User Registered");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Map> loginUser() {
        Map<String, String> map = new HashMap<>();
        map.put("Message", "User LoggedIn");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
