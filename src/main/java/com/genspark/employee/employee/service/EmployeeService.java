package com.genspark.employee.employee.service;

import com.genspark.employee.employee.Employee;
import com.genspark.employee.employee.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public Employee findEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalStateException("Employee with employeeId " + employeeId + " is not present"));
    }

    public void registerANewEmployee(Employee employee) {

        Optional<Employee> existingEmployee = employeeRepository.findEmployeeByEmail(employee.getEmployeeEmail());
        System.out.println(existingEmployee);
        if(existingEmployee.isPresent()) {
            throw new IllegalStateException("Email already exist");
        }

        employeeRepository.save(employee);
    }

    public void removeAEmployee(Long employeeId) {
        boolean existingEmployee = employeeRepository.existsById(employeeId);
        if(!existingEmployee) {
            throw new IllegalStateException("Employee with id " + employeeId + " is not present");
        }

        employeeRepository.deleteById(employeeId);
    }

    @Transactional
    public void updateAnEmployee(Long employeeId, String employeeName, String employeeEmail) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalStateException("Employee with employeeId " + employeeId + " is not present"));

        if (employeeName != null && !employeeName.isEmpty() && !employeeName.equals(employee.getEmployeeName())) {
            employee.setEmployeeName(employeeName);
        }

        if (employeeEmail != null && !employeeEmail.isEmpty() && !employeeEmail.equals(employee.getEmployeeEmail())) {
            Optional<Employee> existingEmployee = employeeRepository.findEmployeeByEmail(employeeEmail);
            if (existingEmployee.isPresent()) {
                throw new IllegalStateException("Email is already taken.");
            }
            employee.setEmployeeEmail(employeeEmail);
        }


        employeeRepository.save(employee);
    }
}
