package com.learning.userservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "Customer")
public class Customer {
    @Id
    private String email;
    private String password;
    @ManyToMany(mappedBy = "customers", fetch = FetchType.EAGER)
    private List<Authority> authorities;
}
