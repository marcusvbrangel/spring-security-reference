package com.mvbr.springsecurity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // curl -u joao:123456 http://localhost:8095/hello
    // curl -u joao:123456 http://localhost:8095/hello -w "%{http_code}"
    @GetMapping("/hello")
    public String hello() {
        return "Hello!";
    }

}
