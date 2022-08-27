package com.learning.userservice.service;

import com.learning.userservice.model.Person;

import java.util.List;

public interface PersonService {
    Person updateCityAddressDaoService(String personId, String city);

    List<Person> getAllPersonWithAddressService();
}
