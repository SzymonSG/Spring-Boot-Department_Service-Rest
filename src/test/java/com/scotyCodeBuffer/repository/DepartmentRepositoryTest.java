package com.scotyCodeBuffer.repository;

import com.scotyCodeBuffer.entity.Department;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager testEntityManager; //for presist


    @BeforeEach
    void setUp(){

        Department department =
                Department.builder()
                .departmentName("Electrical Enginerring")
                .departmentCode("ME = -011")
                .departmentAddress("USA")
                .build();

        testEntityManager.persist(department);

    }

    @Test
    public void whenFindByID_thenReturnDepartment(){
        Department department = departmentRepository.findById(1L).get();
        assertEquals(department.getDepartmentName(),"Electrical Enginerring");
    }

}