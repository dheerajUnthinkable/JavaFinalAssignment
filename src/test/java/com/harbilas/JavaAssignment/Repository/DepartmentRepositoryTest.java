package com.harbilas.JavaAssignment.Repository;

import com.harbilas.JavaAssignment.Entity.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class DepartmentRepositoryTest {

    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    void getDepartmentByName()
    {
        Department department = new Department("SALES", "Vatsal Aggarwal");
        departmentRepository.save(department);

        assertThat(departmentRepository.getDepartmentByName("SALES").get().getHead_of_dept()).isEqualTo("Vatsal Aggarwal");
    }
}