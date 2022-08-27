package com.learning.userservice.dao;

import com.learning.userservice.model.Person;

import java.util.List;

public interface PersonDao {
    Person updateCityAddressDao(String personId, String city);

    List<Person> getAllPersonWithAddressDAO();
}
