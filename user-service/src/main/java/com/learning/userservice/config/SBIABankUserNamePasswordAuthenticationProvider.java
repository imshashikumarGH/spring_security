package com.learning.userservice.config;

import com.learning.userservice.entity.Customer;
import com.learning.userservice.jpaRepository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SBIABankUserNamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<Customer> customers = customerRepository.findByEmail(userName);
        if (!customers.isEmpty()) {
            if (passwordEncoder.matches(password, customers.get(0).getPassword())) {
                List<GrantedAuthority> authorityList = new ArrayList<>();
                authorityList.add(new SimpleGrantedAuthority(customers.get(0).getRole()));
                return new UsernamePasswordAuthenticationToken(userName, password, authorityList);
            } else {
                throw new BadCredentialsException("Invalid Credentials");
            }

        } else {
            throw new BadCredentialsException("No User Found with userid -" + userName);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
