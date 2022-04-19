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
        //Just a normal comment
    }

    @GetMapping(path = "findDepartment")
    public List<Department> getDepartments()
    {
        return departmentService.getDepartments();
    }

    @GetMapping(path = "findDepartment/{deptID}")
    public Department getDepartment(@PathVariable Long deptID)
    {
        return departmentService.getDepartment(deptID);
    }

    @GetMapping(path = "findEmployees/{deptID}")
    public List<String> getEmployeesByDeptID(@PathVariable Long deptID)
    {
        return departmentService.getEmployeesByDeptID(deptID);
    }

    @PostMapping
    public void saveDepartments(@RequestBody Department department)
    {
        departmentService.saveDepartments(department);
    }

    @PutMapping(path = "updateDept/{departmentID}")
    public void updateDepartment(@PathVariable Long departmentID, @RequestBody Department department)
    {
        departmentService.updateDepartment(departmentID, department);
    }

    @DeleteMapping(path = "deleteDept/{departmentID}")
    public void deleteDepartment(@PathVariable Long departmentID)
    {
        departmentService.deleteDepartment(departmentID);
    }

}
