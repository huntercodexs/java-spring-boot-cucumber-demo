package com.huntercodexs.cucumberdemo.employee.service;

import com.huntercodexs.cucumberdemo.employee.dto.EmployeeDTO;
import com.huntercodexs.cucumberdemo.employee.entity.EmployeeEntity;
import com.huntercodexs.cucumberdemo.employee.mapper.EmployeeMapper;
import com.huntercodexs.cucumberdemo.employee.repository.EmployeeRepository;
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
    private EmployeeRepository employeeRepository;

    @Validated
    @Transactional
    public Long create(@Valid EmployeeDTO employeeDTO) {

        EmployeeEntity employeeEntity = mapper.toEmployeeEntity(employeeDTO);
        employeeRepository.save(employeeEntity);

        return employeeEntity.getId();

    }

}