
package com.harbilas.JavaAssignment.Controller;

import com.harbilas.JavaAssignment.DTO.EmployeeDTO;
import com.harbilas.JavaAssignment.Entity.Employee;
import com.harbilas.JavaAssignment.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees()
    {
        return employeeService.getEmployees();
    }

    @PostMapping
    public void saveEmployees(@RequestBody EmployeeDTO employeeDTO)
    {
        employeeService.saveEmployees(employeeDTO);
    }

    @PutMapping(path = "updateEmp/{empID}")
    public void updateEmployees(@PathVariable Long empID, @RequestBody EmployeeDTO employeeDTO)
    {
        employeeService.updateEmployees(empID, employeeDTO);
    }
}
