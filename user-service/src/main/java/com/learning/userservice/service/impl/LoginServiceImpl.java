package com.learning.userservice.service.impl;

import com.learning.userservice.entity.Customer;
import com.learning.userservice.jpaRepository.CustomerRepository;
import com.learning.userservice.model.CustomerRequest;
import com.learning.userservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class LoginServiceImpl implements LoginService {
    public static String c = "C";
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void registerUser(CustomerRequest customerRequest) {

        String id = c + new Random().nextInt(999999);
        Customer customer = Customer.builder().id(id).email(customerRequest.getEmail()).password(customerRequest.getPassword())
                .role(customerRequest.getRole())
                .build();
        customerRepository.save(customer);

    }
}
