package com.harbilas.JavaAssignment.Controller;

import com.harbilas.JavaAssignment.Entity.Department;
import com.harbilas.JavaAssignment.Entity.Employee;
import com.harbilas.JavaAssignment.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Department> getDepartments()
    {
        return departmentService.getDepartments();
    }

    @PostMapping
    public void saveDepartments(@RequestBody Department department)
    {
        departmentService.saveDepartments(department);
    }

    @PutMapping(path = "updateDept/{departmentID}")
    public void updateDepartment(@PathVariable Long departmentID, @RequestParam String name)
    {
        departmentService.updateDepartment(departmentID, name);
    }

    @DeleteMapping(path = "deleteDept/{departmentID}")
    public void deleteDepartment(@PathVariable Long departmentID)
    {
        departmentService.deleteDepartment(departmentID);
    }

}
