package com.learning.userservice.service.impl;

import com.learning.userservice.dao.PersonDao;
import com.learning.userservice.model.Person;
import com.learning.userservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonDao personDao;

    @Override
    public Person updateCityAddressDaoService(String personId, String city) {
        return personDao.updateCityAddressDao(personId, city);
    }
}
