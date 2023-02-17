package com.learning.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
}
