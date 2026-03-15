package com.aceleradev.nexorder.mapper;

import com.aceleradev.nexorder.dtos.employee.EmployeeCreateRequest;
import com.aceleradev.nexorder.dtos.employee.EmployeeResponse;
import com.aceleradev.nexorder.dtos.employee.EmployeeUpdateRequest;
import com.aceleradev.nexorder.entities.Employee;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastLogin", ignore = true)
    Employee toEntity(EmployeeCreateRequest dto);

    EmployeeResponse toResponse(Employee employee);

    List<EmployeeResponse> toResponseList(List<Employee> employees);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastLogin", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateEmployeeFromDto(EmployeeUpdateRequest dto, @MappingTarget Employee entity);

}
