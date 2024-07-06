package com.uriel.springboot.ems_backend.ems_backend.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.uriel.springboot.ems_backend.ems_backend.dto.EmployeeDto;
import com.uriel.springboot.ems_backend.ems_backend.entity.Employee;
import com.uriel.springboot.ems_backend.ems_backend.exception.ResourceNotFoundException;
import com.uriel.springboot.ems_backend.ems_backend.mapper.EmployeeMapper;
import com.uriel.springboot.ems_backend.ems_backend.repository.EmployeeRepository;
import com.uriel.springboot.ems_backend.ems_backend.service.EmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto); // We convert EmployeeDto to Employee
        Employee savedEmployee = employeeRepository.save(employee); // We use the Save method to save in DB

        return EmployeeMapper.mapToEmployeeDto(savedEmployee); // We convert the savedEmployee to a DTO

    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow( () -> new ResourceNotFoundException("Employee does not exist with given id: " + employeeId) );
        
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map((employee)-> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
            () -> new ResourceNotFoundException("Employee does not exist with given id: " + employeeId)
        );

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee updatedEmployeeObj = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    

}
