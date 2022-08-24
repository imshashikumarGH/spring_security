package com.learning.userservice.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Person {
    @Id
    private String pId;
    private String name;
    private String designation;
    private String address;
}
