package com.huntercodexs.cucumberdemo.employee.mapper;

import com.huntercodexs.cucumberdemo.employee.dto.AddressDTO;
import com.huntercodexs.cucumberdemo.employee.dto.EmployeeDTO;
import com.huntercodexs.cucumberdemo.employee.entity.AddressEntity;
import com.huntercodexs.cucumberdemo.employee.entity.EmployeeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeEntity toEmployeeEntity(EmployeeDTO employeeDTO);
    EmployeeDTO toEmployee(EmployeeEntity employeeEntity);
    AddressEntity toAddressEntity(AddressDTO addressDTO);
    AddressDTO toAddress(AddressEntity addressEntity);
}
