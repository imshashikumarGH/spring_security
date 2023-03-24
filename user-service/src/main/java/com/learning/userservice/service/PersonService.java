package com.learning.userservice.service;

import com.learning.userservice.model.Person;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface PersonService {
    @PreAuthorize("#personId == authentication.principal")
    Person updateCityAddressDaoService(String personId, String city);

    @PreAuthorize("hasRole('ROOT')")
    List<Person> getAllPersonWithAddressService();
}
