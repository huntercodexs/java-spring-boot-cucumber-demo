package com.huntercodexs.javaspringbootcucumber.controller;

import com.huntercodexs.javaspringbootcucumber.dto.EmployeeDTO;
import com.huntercodexs.javaspringbootcucumber.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@CrossOrigin("*")
@RequestMapping("/v1/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> create(@RequestBody EmployeeDTO employeeDTO, UriComponentsBuilder uriComponentsBuilder) {
        Long id = employeeService.create(employeeDTO);
        final URI uri = uriComponentsBuilder.path("/v1/employees/{id}").build(id);
        return ResponseEntity.created(uri).build();
    }

}
