package com.learning.userservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "Person")
public class PersonEntity {
    @Id
    private String pId;
    private String name;
    private String designation;
    //by default OneToOne is EAGER fetch so making it Lazy
    @OneToOne(fetch = FetchType.LAZY)
    private AddressEntity address;
}
