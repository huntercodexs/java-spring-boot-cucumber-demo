package com.huntercodexs.cucumberdemo.employee.controller;

import com.huntercodexs.cucumberdemo.employee.dto.EmployeeDTO;
import com.huntercodexs.cucumberdemo.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping(
            path = "/v1/employees",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> create(
            @RequestBody EmployeeDTO employeeDTO,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Long id = employeeService.create(employeeDTO);
        final URI uri = uriComponentsBuilder.path("/v1/employees/{id}").build(id);
        return ResponseEntity.created(uri).build();
    }

}
