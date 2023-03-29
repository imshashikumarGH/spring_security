package com.learning.userservice.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {
    private String pId;
    private String name;
    private String designation;
    private Address address;
}
