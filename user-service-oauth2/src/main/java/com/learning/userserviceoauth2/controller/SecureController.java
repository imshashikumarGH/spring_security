package com.learning.userserviceoauth2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class SecureController {
    @GetMapping("/")
    public ResponseEntity<String> home(OAuth2AuthenticationToken auth2AuthenticationToken) {
        log.info("User Name is " + auth2AuthenticationToken.getPrincipal());
        return new ResponseEntity("ok", HttpStatus.OK);
    }
}
