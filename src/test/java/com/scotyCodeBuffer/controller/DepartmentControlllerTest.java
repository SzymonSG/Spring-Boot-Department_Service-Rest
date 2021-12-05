package com.scotyCodeBuffer.controller;

import com.scotyCodeBuffer.entity.Department;
import com.scotyCodeBuffer.erorr.DepartmentNotFoundException;
import com.scotyCodeBuffer.service.DeparmentService;
import com.scotyCodeBuffer.service.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentControlller.class)
//mock context created
class DepartmentControlllerTest {
    // for request
    @Autowired
    private MockMvc mockMvc;

    @MockBean // for mocking service layer
    private DeparmentService deparmentService;

    private Department department;

    @BeforeEach
    void setUp() {
         department =Department.builder() /// bez typu Department
                .departmentAddress("Bramaba")
                .departmentCode("CE-07")
                .departmentName("IT")
                .departmentId(1L)
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
        Department inputDepartment =Department.builder()
                .departmentAddress("Bramaba")
                .departmentCode("CE-07")
                .departmentName("IT")
                .build();

        Mockito.when(deparmentService.saveDepartment(inputDepartment))
                .thenReturn(department);

        mockMvc.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"departmentName\":\"IT\",\n" +
                        "    \"departmentAddress\":\"Bramaba\",\n" +
                        "    \"departmentCode\":\"CE-07\"\n" +
                        "}"))
                        .andExpect(status().isOk());
    }

    @Test
    void fetchById() throws Exception {

        Mockito.when(deparmentService.fetchById(1L))
                .thenReturn(department);

        mockMvc.perform(get("/departments/1")
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.departmentName")
                       .value(department.getDepartmentName()));

    }

   //@Test
   //void fetchDepartmentByName() throws Exception {
   //    String name ="IT";
   //    Mockito.when(deparmentService.fetchDepartmentByName(name))
   //            .thenReturn(department);
//
   //    mockMvc.perform(get("/departments/name/IT")
   //            .contentType(MediaType.APPLICATION_JSON))
   //            .andExpect(status().isOk())
   //            .andExpect(jsonPath("$.departmentAddress")
   //                    .value(department.getDepartmentAddress()));
//
   //}
}