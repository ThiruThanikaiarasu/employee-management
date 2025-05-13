package com.genspark.employee.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/employee")
public class EmployeeController {

    @Autowired
    public final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployee() {
        return employeeService.findAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getAEmployeeById(@PathVariable("id") Long employeeId) {
        return employeeService.findEmployeeById(employeeId);
    }

    @PostMapping("/add")
    public void addANewEmployee(@RequestBody Employee employee) {
        employeeService.registerANewEmployee(employee);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteAnEmployee(@PathVariable("id") Long employeeId) {
        employeeService.removeAEmployee(employeeId);
    }

    @PutMapping(path = "/update/{id}")
    public void updateAnEmployee(@PathVariable("id") Long employeeId,@RequestParam String employeeName, String employeeEmail) {
        employeeService.updateAnEmployee(employeeId, employeeName,employeeEmail);
    }
}
