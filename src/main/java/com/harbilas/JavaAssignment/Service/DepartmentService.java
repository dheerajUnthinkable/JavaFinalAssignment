package com.harbilas.JavaAssignment.Service;

import com.harbilas.JavaAssignment.Entity.Department;
import com.harbilas.JavaAssignment.Repsitory.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }


    public void saveDepartments(Department department) {
        if(department.getName().trim().equals(""))
        {
            throw new IllegalStateException("Department name cannot be empty!!");
        }
        department.setName(department.getName().toUpperCase());
        Optional<Department> departmentOptional = departmentRepository.getDepartmentByName(department.getName());

        if(departmentOptional.isPresent())
        {
            throw new IllegalStateException(department.getName() + " department already exists.");
        }

        departmentRepository.save(department);
    }

    @Transactional
    public void updateDepartment(Long departmentID, String name) {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentID);
        if(departmentOptional.isEmpty())
        {
            throw new IllegalStateException("There is no department with ID: " + departmentID + ".");
        }
        name = name.trim().toUpperCase();
        if(name.length() == 0)
        {
            throw new IllegalStateException("Department name cannot be empty!!");
        }
        else if(name.equals(departmentOptional.get().getName()))
        {
            throw new IllegalStateException("You have entered the same department name!!");
        }
        else
        {
            Optional<Department> optional = departmentRepository.getDepartmentByName(name);
            if (optional.isPresent())
            {
                throw new IllegalStateException(name + " department already exists.");
            }
            departmentOptional.get().setName(name);
        }
    }

    public void deleteDepartment(Long departmentID) {
        try {
            Optional<Department> departmentOptional = departmentRepository.findById(departmentID);
            if (departmentOptional.isEmpty()) {
                throw new IllegalStateException("There is no department with ID: " + departmentID + ".");
            }

            departmentRepository.delete(departmentOptional.get());
        }
        catch(Exception e)
        {
            throw new IllegalStateException("You cannot delete department with ID: " + departmentID + " from Departments table. This department has some related records in the Employees table.");
        }
    }
}
