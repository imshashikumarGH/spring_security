package com.learning.userservice.exception;

import com.learning.userservice.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionMapper {


    @ExceptionHandler(value = BadRequestException.class)
    @ResponseBody
    public ResponseEntity<?> handleException(Exception e, WebRequest request, HttpServletResponse response) {
        log.error("BadRequestException - {} ", e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().message(e.getMessage()).dateTime(LocalDateTime.now()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    @ResponseBody
    public ResponseEntity<?> handleBadCredentialsException(Exception e, WebRequest request, HttpServletResponse response) {
        log.error("BadRequestException - {} ", e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder().message(e.getMessage()).dateTime(LocalDateTime.now()).build(), HttpStatus.UNAUTHORIZED);
    }

}
