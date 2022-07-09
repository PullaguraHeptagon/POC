package com.heptagon.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    private final Logger log = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/")
    public List<String> getAppStatus() {
        return Arrays.asList("The service is up");
    }

}
