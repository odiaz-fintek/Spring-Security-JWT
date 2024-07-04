package com.auth.jwt.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api")
public class ApiController {

    @GetMapping("/apikey")
    public String testEndpoint() {
        return "Access granted to secure endpoint!";
    }
}