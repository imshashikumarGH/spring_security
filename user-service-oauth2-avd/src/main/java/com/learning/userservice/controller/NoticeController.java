package com.learning.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/notice")
public class NoticeController {
    public static String MESSAGE = "Message";

    @GetMapping(value = "/getBankNotice")
    public ResponseEntity<?> getAllPersonWithAddress() {
        Map map = new HashMap<String, String>();

        map.put(MESSAGE, "!!! No Imp Notice !!!");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
