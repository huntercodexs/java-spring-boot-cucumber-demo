package com.huntercodexs.javaspringbootcucumber.service;

import com.huntercodexs.javaspringbootcucumber.dto.EmployeeDTO;
import com.huntercodexs.javaspringbootcucumber.entity.EmployeeEntity;
import com.huntercodexs.javaspringbootcucumber.mapper.EmployeeMapper;
import com.huntercodexs.javaspringbootcucumber.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class EmployeeService {

    @Autowired
    EmployeeMapper mapper;

    @Autowired
    EmployeeRepository employeeRepository;

    @Validated
    @Transactional
    public Long create(@Valid EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = mapper.toEmployeeEntity(employeeDTO);
        employeeRepository.save(employeeEntity);
        return employeeEntity.getId();
    }

}
