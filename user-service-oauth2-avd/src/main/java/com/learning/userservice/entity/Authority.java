package com.learning.userservice.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data

@Table(name = "Authorities")
public class Authority {
    @Id
    private String name;
    @ManyToMany
    private List<Customer> customers;

    public Authority() {
    }

    public Authority(String name) {
        this.name = name;
    }
}
