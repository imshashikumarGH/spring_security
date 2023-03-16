package com.learning.userservice.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "Customer")
public class Customer {
    @Id
    private String id;
    private String email;
    private String password;
    private String role;
}
