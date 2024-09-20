package com.huntercodexs.javaspringbootcucumber.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class CounterController {

    @GetMapping("/api/counter/{counter}")
    public ResponseEntity<String> counter(@PathVariable(name = "counter") String counter) {

        return switch (counter) {
            case "1" -> ResponseEntity.ok().body("One...");
            case "2" -> ResponseEntity.ok().body("Two...");
            case "3" -> ResponseEntity.ok().body("Three...");
            default -> ResponseEntity.ok().body("Go !");
        };

    }

}
