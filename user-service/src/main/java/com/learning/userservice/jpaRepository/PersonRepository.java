package com.learning.userservice.jpaRepository;

import com.learning.userservice.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, String> {

    @Query(value = "Select p  from person p join fetch p.address a", nativeQuery = true)
    List<PersonEntity> personWithAddress();

};

