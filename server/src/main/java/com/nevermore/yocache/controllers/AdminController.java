package com.nevermore.yocache.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nevermore
 * @since 0.0.1
 */
@RestController
@RequestMapping("/_admin")
public class AdminController {

    @GetMapping("info")
    public ResponseEntity<String> info() {
        return ResponseEntity.ok("Yocache");
    }
}
