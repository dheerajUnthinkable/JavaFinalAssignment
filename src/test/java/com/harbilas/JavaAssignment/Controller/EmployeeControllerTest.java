package com.harbilas.JavaAssignment.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harbilas.JavaAssignment.Repository.DepartmentRepository;
import com.harbilas.JavaAssignment.Repository.EmployeeRepository;
import com.harbilas.JavaAssignment.Service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private DepartmentRepository departmentRepository;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

//    @BeforeEach
//    public void setUp() throws Exception{
//        mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
//                .build();
//    }

    @Test
    void getEmployees() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/employees/findEmployee"))
                .andExpect(status().isOk());
    }

    @Test
    void getEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/employees/findEmployee/{empID}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void saveEmployees() throws Exception {
        String json = "{\n" +
                "  \"departmentIDs\": [\n" +
                "    0\n" +
                "  ],\n" +
                "  \"dob\": \"2000-01-14\",\n" +
                "  \"email\": \"string\",\n" +
                "  \"name\": \"string\",\n" +
                "  \"phoneNumber\": \"string\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateEmployees() throws Exception {
        String json = "{\n" +
                "  \"departmentIDs\": [\n" +
                "    0\n" +
                "  ],\n" +
                "  \"dob\": \"2000-01-14\",\n" +
                "  \"email\": \"string\",\n" +
                "  \"name\": \"string\",\n" +
                "  \"phoneNumber\": \"string\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.put("/employees/updateEmp/{empID}", 1L)
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteEmployees() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/deleteEmp/{empID}",(long)1))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}