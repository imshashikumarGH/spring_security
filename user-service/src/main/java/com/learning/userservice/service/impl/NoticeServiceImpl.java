package com.learning.userservice.service.impl;

import com.learning.userservice.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NoticeServiceImpl implements NoticeService {
    @Override
    public String getNotice() {
        log.info(">>>>>>getNotice() getting message");
        return "NO notice";
    }
}
