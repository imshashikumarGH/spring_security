package com.learning.userservice.config;

import com.learning.userservice.filter.CsrfCookieFilter;
import com.learning.userservice.filter.LogUserFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        HttpSessionCsrfTokenRepository requestHandler = new HttpSessionCsrfTokenRepository();
        requestHandler.setParameterName("_csrf");
        // this will apply for all controllers
        http.securityContext().requireExplicitSave(false)
                .and().sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .cors().configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:5432"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                })
                .and()
                .csrf((csrf) -> csrf.csrfTokenRepository(requestHandler).ignoringRequestMatchers(new RequestMatcher() {
                    @Override
                    public boolean matches(HttpServletRequest request) {

                        if (request.getRequestURI().startsWith("/customer/register")) {
                            return true;
                        }
                        return false;
                    }
                }).csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new LogUserFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests()
//                .antMatchers(HttpMethod.POST, "/account/updateAccountAddress").authenticated()
//                .antMatchers("/account/getAllAccounts").authenticated()
                .requestMatchers(new RequestMatcher() {
                    @Override
                    public boolean matches(HttpServletRequest request) {

                        if (request.getRequestURI().startsWith("/account/updateAccountAddress"))
                            return true;
                        return false;
                    }
                }).hasAnyRole("USER", "ADMIN")
                .requestMatchers(new RequestMatcher() {
                    @Override
                    public boolean matches(HttpServletRequest request) {
                        if (request.getRequestURI().startsWith("/account/getAllAccounts"))
                            return true;
                        return false;
                    }
                }).hasRole("ADMIN")
                .requestMatchers(new RequestMatcher() {
                    @Override
                    public boolean matches(HttpServletRequest request) {
                        if (request.getRequestURI().matches("/swagger-ui/index.html")
                                || request.getRequestURI().matches("/notice/getBankNotice")
                                || request.getRequestURI().matches("/customer/register"))
                            return true;
                        return false;
                    }
                }).permitAll()
                .and().formLogin()
                .and().httpBasic();
        return http.build();
    }

    //Bcrypt encryption
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
