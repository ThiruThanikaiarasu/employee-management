package com.genspark.employee.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity getAllEmployee() {
        ResponseEntity result;
        try {
            List<Employee> employees = employeeService.findAllEmployees();
            result = ResponseEntity.ok(employees);
        } catch(Exception e) {
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return result;
    }

    @GetMapping("/{id}")
    public ResponseEntity getAEmployeeById(@PathVariable("id") Long employeeId) {
        ResponseEntity result;
        try {
            Employee employee = employeeService.findEmployeeById(employeeId);
            result = ResponseEntity.ok(employee);
        } catch (Exception e) {
            result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return result;
    }

    @PostMapping("/add")
    public ResponseEntity addANewEmployee(@RequestBody Employee employee) {
        ResponseEntity result;
        try {
            employeeService.registerANewEmployee(employee);
            result = ResponseEntity.status(HttpStatus.CREATED).body("Employee created successfully");
        } catch (Exception e) {
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return result;
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity deleteAnEmployee(@PathVariable("id") Long employeeId) {
        ResponseEntity result;
        try {
            employeeService.removeAEmployee(employeeId);
            result = ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception e) {
            result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return result;
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity updateAnEmployee(@PathVariable("id") Long employeeId,@RequestParam String employeeName, String employeeEmail) {
        ResponseEntity result;
        try {
            employeeService.updateAnEmployee(employeeId, employeeName, employeeEmail);
            result = ResponseEntity.ok("Employee updated successfully");
        } catch (Exception e) {
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return result;
    }
}
