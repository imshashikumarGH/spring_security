package com.learning.userservice.dao;

import com.learning.userservice.model.Person;

public interface PersonDao {
    Person updateCityAddressDao(String personId, String city);
}
