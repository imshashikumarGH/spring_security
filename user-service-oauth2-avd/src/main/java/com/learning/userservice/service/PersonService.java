package com.learning.userservice.service;

import com.learning.userservice.model.Person;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface PersonService {
    @PreAuthorize("#personId == authentication.principal")
    Person updateCityAddressDaoService(String personId, String city);


    //Filter only works on collections
    //preFilter on collection parameter
    //postFilter on collection return
    @PreAuthorize("hasRole('ADMIN')")
    @PostFilter("filterObject.pId == authentication.principal")
    List<Person> getAllPersonWithAddressService();
}
