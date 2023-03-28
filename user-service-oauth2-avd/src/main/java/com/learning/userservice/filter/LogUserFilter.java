package com.learning.userservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
public class LogUserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            log.info("User with user name" + authentication.getName() + "has Roles - " + authentication.getAuthorities().toString());
        }
        chain.doFilter(request, response);
    }
}
