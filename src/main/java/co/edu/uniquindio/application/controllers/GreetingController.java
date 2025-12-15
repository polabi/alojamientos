package co.edu.uniquindio.application.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @GetMapping
    public String greet() {
        return "Hola, bienvenido a Placey";
    }

    @GetMapping("/{name}")
    public String greetName(@PathVariable String name) {
        return "Hola %s, bienvenido a Placey".formatted(name);
    }
}

