package com.nevermore.yocache.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author nevermore
 * @since 0.0.1
 */
@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<String> exceptionHandler(Throwable exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>("ops", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<String> exceptionHandler(IllegalArgumentException exception) {
        log.warn(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
