package com.harbilas.JavaAssignment.Repsitory;

import com.harbilas.JavaAssignment.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("Select d from Department d where d.name = ?1")
    Optional<Department> getDepartmentByName(String name);
}
