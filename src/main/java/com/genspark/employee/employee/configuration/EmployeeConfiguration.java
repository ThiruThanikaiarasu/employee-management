package com.genspark.employee.employee.configuration;

import com.genspark.employee.employee.Employee;
import com.genspark.employee.employee.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class EmployeeConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository) {
        return args -> {
            Employee thiru = new Employee(
                    "Thiru",
                    "thiru@gmail.com",
                    LocalDate.of(2004, Month.APRIL, 21)

            );

            employeeRepository.save(thiru);
        };
    }
}
