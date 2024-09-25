package com.huntercodexs.cucumberdemo.employee.repository;

import com.huntercodexs.cucumberdemo.employee.entity.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {}
