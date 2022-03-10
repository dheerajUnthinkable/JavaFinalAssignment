package com.harbilas.JavaAssignment.Repsitory;

import com.harbilas.JavaAssignment.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("Select e from Employee e where e.email = ?1")
    Optional<Employee> findEmployeebyEmail(String Email);
}
