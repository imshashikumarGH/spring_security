package com.learning.userservice.dao.impl;

import com.learning.userservice.dao.PersonDao;
import com.learning.userservice.entity.AddressEntity;
import com.learning.userservice.entity.PersonEntity;
import com.learning.userservice.exception.BadRequestException;
import com.learning.userservice.jpaRepository.AddressRepository;
import com.learning.userservice.jpaRepository.PersonRepository;
import com.learning.userservice.model.Address;
import com.learning.userservice.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
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
            throw new BadRequestException("Id not found");
        }
    }

    @Override
    public List<Person> getAllPersonWithAddressDAO() {
//        List<PersonEntity> personEntitiesList = personRepository.findAll();
//        personEntitiesList.stream().forEach(personEntity -> personEntity.getAddress());
        List<PersonEntity> personEntitiesList = personRepository.personWithAddress();
        List<Person> personList = new ArrayList<>();
        personEntitiesList.forEach(personEntity -> personList.add(Person.builder()
                .pId(personEntity.getPId())
                .name(personEntity.getName())
                .designation(personEntity.getDesignation())

                .address(Address.builder()
                        .id(personEntity.getAddress().getId())
                        .country(personEntity.getAddress().getCountry())
                        .city(personEntity.getAddress().getCity())
                        .build())
                .build()));
        return personList;
    }

    @Override
    public List<Person> getAllPersonWithAddressSortedDAO() {
        List<PersonEntity> personEntitiesList = personRepository.findAll(Sort.by(Sort.Direction.ASC, "name").and(Sort.by(Sort.Direction.ASC, "designation")));
        personEntitiesList.stream().forEach(personEntity -> personEntity.getAddress());
        List<Person> personList = new ArrayList<>();
        personEntitiesList.forEach(personEntity -> personList.add(Person.builder()
                .pId(personEntity.getPId())
                .name(personEntity.getName())
                .designation(personEntity.getDesignation())

                .address(Address.builder()
                        .id(personEntity.getAddress().getId())
                        .country(personEntity.getAddress().getCountry())
                        .city(personEntity.getAddress().getCity())
                        .build())
                .build()));
        return personList;
    }
}
