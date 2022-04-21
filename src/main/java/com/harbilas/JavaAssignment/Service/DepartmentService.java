package com.harbilas.JavaAssignment.Service;

import com.harbilas.JavaAssignment.Entity.Department;
import com.harbilas.JavaAssignment.Entity.Employee;
import com.harbilas.JavaAssignment.ExceptionClasses.NoDepartmentExists;
import com.harbilas.JavaAssignment.Repository.DepartmentRepository;
import com.harbilas.JavaAssignment.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }


    public boolean saveDepartments(Department department) {
        if(department.getName().trim().equals(""))
        {
            throw new IllegalStateException("Department name cannot be empty!!");
        }

        if(department.getHead_of_dept().trim().equals(""))
        {
            throw new IllegalStateException("Head's name cannot be empty!!");
        }
        department.setName(department.getName().toUpperCase());
        department.setHead_of_dept(department.getHead_of_dept().toUpperCase());
        Optional<Department> departmentOptional = departmentRepository.getDepartmentByName(department.getName());

        if(departmentOptional.isPresent())
        {
            throw new IllegalStateException(department.getName() + " department already exists.");
        }

        departmentRepository.save(department);
        return true;
    }

    @Transactional
    public void updateDepartment(Long departmentID, Department department) {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentID);
        if(departmentOptional.isEmpty())
        {
            throw new NoDepartmentExists(departmentID.toString());
        }

        String name = department.getName() != null ? department.getName().trim().toUpperCase() : "";
        String hod = department.getHead_of_dept() != null ? department.getHead_of_dept().trim().toUpperCase() : "";

        if(name.length() == 0 && hod.length() == 0)
        {
            throw new IllegalStateException("There is nothing to update..");
        }


        // Updating Name of the Department

        if(name.equals(departmentOptional.get().getName()))
        {
            throw new IllegalStateException("You have entered the same department name!!");
        }
        if(!name.equals(""))
        {
            Optional<Department> optional = departmentRepository.getDepartmentByName(name);
            if (optional.isPresent())
            {
                throw new IllegalStateException(name + " department already exists.");
            }
            departmentOptional.get().setName(name);
        }

        // Updating Head of the Department

        if(hod.equals(departmentOptional.get().getHead_of_dept()))
        {
            throw new IllegalStateException("You have entered the same name for the Head!!");
        }
        if (!hod.equals(""))
        {
            departmentOptional.get().setHead_of_dept(hod);
        }

    }

    public void deleteDepartment(Long departmentID) {
        try {
            Optional<Department> departmentOptional = departmentRepository.findById(departmentID);
            if (departmentOptional.isEmpty()) {
                throw new NoDepartmentExists(departmentID.toString());
            }

            departmentRepository.delete(departmentOptional.get());
        }
        catch(Exception e)
        {
            throw new IllegalStateException("You cannot delete department with ID: " + departmentID + " from Departments table. This department has some related records in the Employees table.");
        }
    }

    public Department getDepartment(Long deptID) {
        Optional<Department> optionalDepartment = departmentRepository.findById(deptID);
        if (optionalDepartment.isEmpty()) {
            throw new NoDepartmentExists(deptID.toString());
        }

        Department department = optionalDepartment.get();
        return department;
    }

    public List<String> getEmployeesByDeptID(Long deptID) {
        Optional<Department> departmentOptional = departmentRepository.findById(deptID);
        if (departmentOptional.isEmpty()) {
            throw new NoDepartmentExists(deptID.toString());
        }
        List<Employee> employeeList = employeeRepository.findAll();
        List<String> employeeNameList = new ArrayList<>();
        for(Employee e : employeeList) {
            if(e.getDepartments().contains(departmentOptional.get()))
            {
                employeeNameList.add(e.getName());
            }
        }

        return employeeNameList;
    }
}
