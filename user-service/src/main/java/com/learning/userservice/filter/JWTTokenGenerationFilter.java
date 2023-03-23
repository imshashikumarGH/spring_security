package com.learning.userservice.filter;

import com.learning.userservice.util.SecurityConstant;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class JWTTokenGenerationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            SecretKey secretKey = Keys.hmacShaKeyFor(SecurityConstant.KEY.getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts.builder().setIssuer("SBank").setSubject("JWT token")
                    .claim("username", authentication.getName())
                    .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + 3000000))
                    .signWith(secretKey)
                    .compact();
            response.setHeader(SecurityConstant.JWT_HEADER_NAME, jwt);
        }
        filterChain.doFilter(request, response);
    }

    //only execute at the time of login
    //controlling the executed of filter using shouldNotFilter
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().startsWith("/customer/login");
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        HashSet<String> authoritiesSet = new HashSet<>();
        authorities.forEach(grantedAuthority -> authoritiesSet.add(((GrantedAuthority) grantedAuthority).getAuthority()));
        return String.join(",", authoritiesSet);
    }
}
