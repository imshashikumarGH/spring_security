package com.learning.userservice.config;

import com.learning.userservice.filter.CsrfCookieFilter;
import com.learning.userservice.filter.JWTTokenGenerationFilter;
import com.learning.userservice.filter.JWTTokenValidatorFilter;
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
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;

import static com.learning.userservice.util.SecurityConstant.JWT_HEADER_NAME;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CookieCsrfTokenRepository requestHandler = new CookieCsrfTokenRepository();
        requestHandler.setParameterName("_csrf");

        //To spring : Not to create JSession ID and not to save any session id details
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors().configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:5432"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));

                        // To let the UI know about our custom header and expose it.
                        config.setExposedHeaders(Arrays.asList(JWT_HEADER_NAME));
                        config.setMaxAge(3600L);
                        return config;
                    }
                })
                .and()
                .csrf((csrf) -> csrf.csrfTokenRepository(requestHandler).ignoringRequestMatchers(new RequestMatcher() {
                    @Override
                    public boolean matches(HttpServletRequest request) {

                        if (request.getRequestURI().startsWith("/customer/register") || request.getRequestURI().startsWith("/customer/login")) {
                            return true;
                        }
                        return false;
                    }
                }).csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new LogUserFilter(), BasicAuthenticationFilter.class)
                //token generation after BasicAuthenticationFilter
                .addFilterAfter(new JWTTokenGenerationFilter(), BasicAuthenticationFilter.class)
                //token validation after BasicAuthenticationFilter
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
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
                }).authenticated()
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
