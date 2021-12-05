package com.scotyCodeBuffer.service;

import com.scotyCodeBuffer.entity.Department;
import com.scotyCodeBuffer.repository.DepartmentRepository;
import org.assertj.core.internal.DeepDifference;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //test klas
class DeparmentServiceTest {

    @Autowired
    private DeparmentService deparmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("IT") // przekazywana wartość
                .departmentAddress("StocX")
                .departmentCode("C1-03")
                .departmentId(1L)
                .build();
        // w momencie wyowałania metody z repo zwarcamy obiekt department
        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("IT"))
                .thenReturn(department);
    }

    @Test
    @DisplayName("Get Data based on Valid Department Name")
    public void whenVaildDepartmentName_thenDepartmentShouldFound()  {
        String departmentName= "IT"; /// oczekiwana waretość
        Department found =
                deparmentService.fetchDepartmentByName(departmentName);
        assertEquals(departmentName,found.getDepartmentName());
    }


}