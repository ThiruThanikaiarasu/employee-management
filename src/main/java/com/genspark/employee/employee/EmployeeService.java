package com.genspark.employee.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeService {

    @Autowired
    public final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public void registerANewEmployee(Employee employee) {

        Optional<Employee> existingEmployee = employeeRepository.findEmployeeByEmail(employee.getEmployeeEmail());
        System.out.println(existingEmployee);
        if(existingEmployee.isPresent()) {
            throw new IllegalStateException("Email already exist");
        }

        employeeRepository.save(employee);
    }
}
