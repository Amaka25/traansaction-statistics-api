package com.seerbit.interview_test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {
    @RequestMapping("/")
    String home() {
        return "My application is healthy!";
    }
}
