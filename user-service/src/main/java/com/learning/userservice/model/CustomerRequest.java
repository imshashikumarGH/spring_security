package com.learning.userservice.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class CustomerRequest {
    private String email;
    private String password;
    private String role;

}
