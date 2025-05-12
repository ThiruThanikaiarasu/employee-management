package com.genspark.employee.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT emp from Employee emp where emp.employeeEmail = employeeEmail")
    Optional<Employee> findEmployeeByEmail(String employeeEmail);
}
