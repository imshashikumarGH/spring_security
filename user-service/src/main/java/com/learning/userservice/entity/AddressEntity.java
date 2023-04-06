package com.learning.userservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "Address")
@Cacheable
public class AddressEntity {
    @Id
    @GeneratedValue
    private int id;

    private String city;

    private String country;

    //by default OneToOne is EAGER fetch so making it Lazy
    //mappedBy to make the person table owner of the relationship
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "address")
    private PersonEntity personEntity;
}
