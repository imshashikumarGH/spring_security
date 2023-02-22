package com.learning.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers(new RequestMatcher() {
                    @Override
                    public boolean matches(HttpServletRequest request) {
                        if (request.getRequestURI().matches("/person/getAllPersonWithAddress"))
                            return true;
                        return false;
                    }
                })
                .authenticated()
                .requestMatchers(new RequestMatcher() {
                    @Override
                    public boolean matches(HttpServletRequest request) {
                        if (request.getRequestURI().matches("/swagger-ui/index.html"))
                            return true;
                        return false;
                    }
                }).permitAll()
                .and().formLogin()
                .and().httpBasic();
        return http.build();
    }


//    1st Approach
    // using withDefaultPasswordEncoder
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("shashi")
//                .password("11111")
//                .authorities("admin")
//                .build();
//        UserDetails reader = User.withDefaultPasswordEncoder()
//                .username("ravi")
//                .password("12345")
//                .authorities("read")
//                .build();
//        return new InMemoryUserDetailsManager(admin, reader);
//    }

    // 2nd Approach
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.withUsername("shashi")
                .password("11111")
                .authorities("admin")
                .build();
        UserDetails reader = User.withUsername("ravi")
                .password("12345")
                .authorities("read")
                .build();
        return new InMemoryUserDetailsManager(admin, reader);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
