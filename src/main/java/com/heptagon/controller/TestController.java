package com.heptagon.controller;

import java.util.Arrays;
import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController(value = "/clients")
@SecurityRequirement(name = "pullagura")
public class TestController {

    @GetMapping
    public List<String> getClients() {
        return Arrays.asList("First Client", "Second Client");
    }

}
