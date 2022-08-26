package com.learning.userservice.dao.impl;

import com.learning.userservice.dao.PersonDao;
import com.learning.userservice.entity.AddressEntity;
import com.learning.userservice.entity.PersonEntity;
import com.learning.userservice.jpaRepository.AddressRepository;
import com.learning.userservice.jpaRepository.PersonRepository;
import com.learning.userservice.model.Address;
import com.learning.userservice.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PersonDaoImpl implements PersonDao {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    AddressRepository addressRepository;

    public Person updateCityAddressDao(String personId, String city) {
        Optional<PersonEntity> person = personRepository.findById(personId);
        if (person.isPresent()) {
            AddressEntity addressEntity = person.get().getAddress();
            addressEntity.setCity(city);
            return Person.builder()
                    .pId(personId)
                    .name(person.get().getName())
                    .designation(person.get().getDesignation())
                    .address(Address.builder()
                            .id(addressEntity.getId())
                            .city(addressEntity.getCity())
                            .country(addressEntity.getCountry()).build())
                    .build();
        } else {
            return Person.builder().build();
        }
    }

}
