package com.harbilas.JavaAssignment.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harbilas.JavaAssignment.Repository.DepartmentRepository;
import com.harbilas.JavaAssignment.Repository.EmployeeRepository;
import com.harbilas.JavaAssignment.Service.DepartmentService;
import com.harbilas.JavaAssignment.Service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @MockBean
    private DepartmentRepository departmentRepository;

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private DepartmentService departmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getDepartments() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/departments/findDepartment"))
                .andExpect(status().isOk());
    }

    @Test
    void getDepartment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/departments/findDepartment/{deptID}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void getEmployeesByDeptID() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/departments/findEmployees/{deptID}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void saveDepartments() throws Exception {
        String json = "{\n" +
                "  \"createdBy\": \"string\",\n" +
                "  \"createdOn\": \"2022-03-16T11:15:11.968Z\",\n" +
                "  \"head_of_dept\": \"string\",\n" +
                "  \"id\": 0,\n" +
                "  \"lastModifiedBy\": \"string\",\n" +
                "  \"lastModifiedOn\": \"2022-03-16T11:15:11.968Z\",\n" +
                "  \"name\": \"string\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateDepartment() throws Exception {
        String json = "{\n" +
                "  \"head_of_dept\": \"string\",\n" +
                "  \"name\": \"string\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.put("/departments/updateDept/{departmentID}", 1L)
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteDepartment() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/departments/deleteDept/{departmentID}",(long)1))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}