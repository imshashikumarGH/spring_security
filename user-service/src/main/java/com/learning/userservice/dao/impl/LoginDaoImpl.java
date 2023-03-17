package com.learning.userservice.dao.impl;

import com.learning.userservice.dao.LoginDao;
import com.learning.userservice.entity.Customer;
import com.learning.userservice.jpaRepository.CustomerRepository;
import com.learning.userservice.model.CustomerRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class LoginDaoImpl implements LoginDao {
    @Autowired
    CustomerRepository customerRepository;

    //to encrypt the password of newly register User
    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public void registerUserDao(CustomerRequest customerRequest) {
        //Encrypting the password of newly register User
        String hashPwd = passwordEncoder.encode(customerRequest.getPassword());
        Customer customer = Customer.builder()
                .email(customerRequest.getEmail())
                .password(hashPwd)
                .role(customerRequest.getRole())
                .build();
        customerRepository.save(customer);
    }
}
