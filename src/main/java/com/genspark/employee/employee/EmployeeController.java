package com.genspark.employee.employee;

import com.genspark.employee.employee.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/employee")
public class EmployeeController {

    @Autowired
    public final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Employee>>> getAllEmployee() {
        ResponseEntity<ApiResponse<List<Employee>>> result;
        try {
            List<Employee> employees = employeeService.findAllEmployees();
            ApiResponse<List<Employee>> response = new ApiResponse<>("Employees fetched successfully", employees, null);
            result = ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<Employee>> response = new ApiResponse<>("Failed to fetch employees", null, e.getMessage());
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return result;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> getAEmployeeById(@PathVariable("id") Long employeeId) {
        ResponseEntity<ApiResponse<Employee>> result;
        try {
            Employee employee = employeeService.findEmployeeById(employeeId);
            ApiResponse<Employee> response = new ApiResponse<>("Employee fetched successfully", employee, null);
            result = ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Employee> response = new ApiResponse<>("Failed to fetch employee", null, e.getMessage());
            result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return result;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Void>> addANewEmployee(@RequestBody Employee employee) {
        ResponseEntity<ApiResponse<Void>> result;
        try {
            employeeService.registerANewEmployee(employee);
            ApiResponse<Void> response = new ApiResponse<>("Employee created successfully", null, null);
            result = ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse<Void> response = new ApiResponse<>("Failed to create employee", null, e.getMessage());
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return result;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAnEmployee(@PathVariable("id") Long employeeId) {
        ResponseEntity<ApiResponse<Void>> result;
        try {
            employeeService.removeAEmployee(employeeId);
            ApiResponse<Void> response = new ApiResponse<>("Employee deleted successfully", null, null);
            result = ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);// 204 typically has no body
        } catch (Exception e) {
            ApiResponse<Void> response = new ApiResponse<>("Failed to delete employee", null, e.getMessage());
            result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return result;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Void>> updateAnEmployee(
            @PathVariable("id") Long employeeId,
            @RequestParam String employeeName,
            @RequestParam String employeeEmail) {
        ResponseEntity<ApiResponse<Void>> result;
        try {
            employeeService.updateAnEmployee(employeeId, employeeName, employeeEmail);
            ApiResponse<Void> response = new ApiResponse<>("Employee updated successfully", null, null);
            result = ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<Void> response = new ApiResponse<>("Failed to update employee", null, e.getMessage());
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return result;
    }
}
