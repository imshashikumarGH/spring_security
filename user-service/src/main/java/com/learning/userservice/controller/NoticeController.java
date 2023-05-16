package com.learning.userservice.controller;

import com.learning.userservice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    NoticeService noticeService;

    @GetMapping(value = "/getBankNotice")
    public ResponseEntity<?> getAllPersonWithAddress() {
        Map map = new HashMap<String, String>();
        String megs = noticeService.getNotice();
        map.put(MESSAGE, megs);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
