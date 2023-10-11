package fr.polytech.ApiGateway.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/try")
public class Try {
    @GetMapping("/hello")
    public String index() {
        return "Hello My friend 2!";
    }
}
