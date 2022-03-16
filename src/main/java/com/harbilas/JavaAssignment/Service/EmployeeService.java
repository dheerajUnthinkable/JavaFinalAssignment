package com.harbilas.JavaAssignment.Service;

import com.harbilas.JavaAssignment.DTO.EmployeeDTO;
import com.harbilas.JavaAssignment.Entity.Department;
import com.harbilas.JavaAssignment.Entity.Employee;
import com.harbilas.JavaAssignment.Repository.DepartmentRepository;
import com.harbilas.JavaAssignment.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }


    public boolean saveEmployees(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(
                employeeDTO.getName(),
                employeeDTO.getPhoneNumber(),
                employeeDTO.getDob(),
                employeeDTO.getEmail()
        );
        Optional<Employee> optionalEmployee = employeeRepository.findEmployeebyEmail(employeeDTO.getEmail());
        if(optionalEmployee.isPresent())
        {
            throw new IllegalStateException("Email already taken!!");
        }

        for(Long id : employeeDTO.getDepartmentIDs())
        {
            Optional<Department> optionalDepartment = departmentRepository.findById(id);
            if(optionalDepartment.isEmpty())
            {
                throw new IllegalStateException("Department with ID:- " + id + " does not exist.");
            }
            Department department = optionalDepartment.get();
            employee.getDepartments().add(department);
            department.getEmployees().add(employee);
        }
        employeeRepository.save(employee);
        return true;
    }

    @Transactional
    public boolean updateEmployees(Long empID, EmployeeDTO employeeDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(empID);
        if(optionalEmployee.isEmpty())
        {
            throw new IllegalStateException("Employee with ID: " + empID + " does not exist.");
        }
        Employee employee = optionalEmployee.get();

        if(employeeDTO.getName() != null && employeeDTO.getName().length() > 0)
        {
            employee.setName(employeeDTO.getName());
        }

        if(employeeDTO.getEmail() != null && employeeDTO.getEmail().length() > 0)
        {
            employee.setEmail(employeeDTO.getEmail());
        }

        if(employeeDTO.getDob() != null && employeeDTO.getDob().toString().length() > 0)
        {
            employee.setDob(employeeDTO.getDob());
        }

        if(employeeDTO.getPhoneNumber() != null && employeeDTO.getPhoneNumber().length() > 0)
        {
            employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        }

        if(!employeeDTO.getDepartmentIDs().isEmpty())
        {
            employee.getDepartments().clear();
            for(Long id : employeeDTO.getDepartmentIDs())
            {
                Optional<Department> optionalDepartment = departmentRepository.findById(id);
                if(optionalDepartment.isEmpty())
                {
                    throw new IllegalStateException("Department with ID:- " + id + " does not exist.");
                }
                Department department = optionalDepartment.get();
                employee.getDepartments().add(department);
                department.getEmployees().add(employee);
            }
        }

        return true;
    }

    public void deleteEmployees(Long empID) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(empID);
        if(optionalEmployee.isEmpty())
        {
            throw new IllegalStateException("Employee with ID: " + empID + " does not exist.");
        }
        employeeRepository.deleteById(empID);
    }

    public Employee getEmployee(Long empID) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(empID);
        if(optionalEmployee.isEmpty())
        {
            throw new IllegalStateException("Employee with Employee ID: " + empID + " does not exist.");
        }

        Employee employee = optionalEmployee.get();

        return employee;
    }
}
