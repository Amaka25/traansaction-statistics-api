package com.seerbit.interview_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class InterviewTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterviewTestApplication.class, args);
    }

}