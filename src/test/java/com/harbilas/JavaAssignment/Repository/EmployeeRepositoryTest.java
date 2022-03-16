package com.harbilas.JavaAssignment.Repository;

import com.harbilas.JavaAssignment.Entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void findEmployeebyEmail() {
        Employee employee = new Employee("Harbilas Singh", "9780612025", LocalDate.of(2000, Month.JANUARY, 14), "singhharbilas2000@gmail.com");
        employeeRepository.save(employee);
        assertThat(employeeRepository.findEmployeebyEmail("singhharbilas2000@gmail.com").get().getName()).isEqualTo("Harbilas Singh");
    }

}