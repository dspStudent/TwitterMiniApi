package com.twitter.mini.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*")
@RestController
public class HelloController {
    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hello");
    }
}
