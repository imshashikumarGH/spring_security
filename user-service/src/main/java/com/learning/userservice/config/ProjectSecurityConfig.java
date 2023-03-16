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
        http.csrf().disable().authorizeHttpRequests()
//                .antMatchers(HttpMethod.POST, "/account/updateAccountAddress").authenticated()
//                .antMatchers("/account/getAllAccounts").authenticated()
                .requestMatchers(new RequestMatcher() {
                    @Override
                    public boolean matches(HttpServletRequest request) {
                        if (request.getRequestURI().startsWith("/account/"))
                            return true;
                        return false;
                    }
                })
                .hasAuthority("admin")
                .requestMatchers(new RequestMatcher() {
                    @Override
                    public boolean matches(HttpServletRequest request) {
                        if (request.getRequestURI().matches("/swagger-ui/index.html"))
                            return true;
                        if (request.getRequestURI().matches("/notice/getBankNotice"))
                            return true;
                        return false;
                    }
                }).permitAll()
                .and().formLogin()
                .and().httpBasic();
        return http.build();
    }


//
//    1st Approach
//    to add users  using withDefaultPasswordEncoder
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


    //    2nd Approach
//    use to add n number  users with separated bean of password encoder below
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails admin = User.withUsername("shashi")
//                .password("11111")
//                .authorities("admin")
//                .build();
//        UserDetails reader = User.withUsername("ravi")
//                .password("12345")
//                .authorities("read")
//                .build();
//        return new InMemoryUserDetailsManager(admin, reader);
//    }
//
//    // not recommended for production since password is plane text
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
}
