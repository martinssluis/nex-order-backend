package com.aceleradev.backend.services;

import com.aceleradev.backend.commons.dto.EmployeeDto;
import com.aceleradev.backend.entities.Employee;
import com.aceleradev.backend.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public List<EmployeeDto> findAll() {
        List<Employee> employees = repository.findAll();
        EmployeeDto dto = new EmployeeDto();
        return employees.stream().map(
                it -> {
                    dto.setName(it.getName());
                    dto.setActive(it.getActive());
                    dto.setPhoneNumber(it.getPhoneNumber());
                    dto.setEmail(it.getEmail());
                    dto.setDescription(it.getDescription());
                    dto.setLastLogin(it.getLastLogin());
                    dto.setBaseSalary(it.getBaseSalary());
                    dto.setRole(it.getRole());
                    return dto;
                }
        ).toList();
    }

    public EmployeeDto findById(Long id) {
        Optional<Employee> employee = repository.findById(id);
        EmployeeDto dto = new EmployeeDto();
        Employee obj = employee.get();

        dto.setName(obj.getName());
        dto.setActive(obj.getActive());
        dto.setPhoneNumber(obj.getPhoneNumber());
        dto.setEmail(obj.getEmail());
        dto.setDescription(obj.getDescription());
        dto.setLastLogin(obj.getLastLogin());
        dto.setBaseSalary(obj.getBaseSalary());
        dto.setRole(obj.getRole());

        return dto;

    }

    public void createEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();

        employee.setName(employeeDto.getName());
        employee.setPassword(employeeDto.getPassword());
        employee.setActive(employeeDto.getActive());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setEmail(employeeDto.getEmail());
        employee.setDescription(employeeDto.getDescription());
        employee.setLastLogin(employeeDto.getLastLogin());
        employee.setBaseSalary(employeeDto.getBaseSalary());
        employee.setRole(employeeDto.getRole());

        repository.save(employee);
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }

    public void updateEmployee(Long id, EmployeeDto employeeDto) {
        Optional<Employee> employeeById = repository.findById(id);

        if (employeeById.isPresent()) {
            Employee employee = employeeById.get();
            employee.setName(employeeDto.getName());
            employee.setPassword(employeeDto.getPassword());
            employee.setActive(employeeDto.getActive());
            employee.setPhoneNumber(employeeDto.getPhoneNumber());
            employee.setEmail(employeeDto.getEmail());
            employee.setDescription(employeeDto.getDescription());
            employee.setLastLogin(employeeDto.getLastLogin());
            employee.setBaseSalary(employeeDto.getBaseSalary());
            employee.setRole(employeeDto.getRole());

            repository.save(employee);
        } else {
            throw new RuntimeException("Employee not found");
        }
    }
}
