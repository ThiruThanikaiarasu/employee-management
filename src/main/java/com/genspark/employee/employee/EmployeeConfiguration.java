package com.genspark.employee.employee;

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
