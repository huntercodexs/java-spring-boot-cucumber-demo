package com.huntercodexs.javaspringbootcucumber.mapper;

import com.huntercodexs.javaspringbootcucumber.dto.AddressDTO;
import com.huntercodexs.javaspringbootcucumber.dto.EmployeeDTO;
import com.huntercodexs.javaspringbootcucumber.entity.AddressEntity;
import com.huntercodexs.javaspringbootcucumber.entity.EmployeeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeEntity toEmployeeEntity(EmployeeDTO employeeDTO);
    EmployeeDTO toEmployee(EmployeeEntity employeeEntity);
    AddressEntity toAddressEntity(AddressDTO addressDTO);
    AddressDTO toAddress(AddressEntity addressEntity);
}
