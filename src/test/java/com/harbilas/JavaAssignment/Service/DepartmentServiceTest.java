package com.harbilas.JavaAssignment.Service;

import com.harbilas.JavaAssignment.DTO.EmployeeDTO;
import com.harbilas.JavaAssignment.Entity.Department;
import com.harbilas.JavaAssignment.Repository.DepartmentRepository;
import com.harbilas.JavaAssignment.Repository.EmployeeRepository;
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
class DepartmentServiceTest {

    @MockBean
    DepartmentRepository departmentRepository;

    @InjectMocks
    DepartmentService departmentService;

    @Test
    void getDepartments() {
        departmentService.getDepartments();
        verify(departmentRepository).findAll();
    }

    @Test
    void saveDepartments() {
        Department department = new Department("SALES", "SANJEEV KAPPOOR");
        boolean isSaved = departmentService.saveDepartments(department);
        assertThat(isSaved).isTrue();
    }

    @Test
    void updateDepartment() {
    }

    @Test
    void deleteDepartment() {
    }

    @Test
    void getDepartment() {
    }

    @Test
    void getEmployeesByDeptID() {
    }
}