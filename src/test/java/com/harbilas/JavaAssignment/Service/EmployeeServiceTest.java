package com.harbilas.JavaAssignment.Service;

import com.harbilas.JavaAssignment.DTO.EmployeeDTO;
import com.harbilas.JavaAssignment.Entity.Employee;
import com.harbilas.JavaAssignment.Repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class EmployeeServiceTest {

    @MockBean
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeService employeeService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void saveEmployees() {
        EmployeeDTO employeeDTO = new EmployeeDTO("Dheeraj Kapoor", "9464541057", LocalDate.of(2001, Month.JULY, 07), "dkdasuya7@gmail.com");
        boolean isSaved = employeeService.saveEmployees(employeeDTO);
        assertThat(isSaved).isEqualTo(true);
    }

    @Test
    void getEmployees() {
        employeeService.getEmployees();
        verify(employeeRepository).findAll();
    }

    @Test
    void testSaveEmployees() {
    }

    @Test
    void deleteEmployees() {
    }

    @Test
    void getEmployee() {

    }

    @Test
    void updateEmployees() {

    }
}