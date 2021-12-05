package com.scotyCodeBuffer.repository;

import com.scotyCodeBuffer.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

    public Department findByDepartmentName(String departmentName);
    //findBy<name filed in Entity start UPPER CASE name convention

    //@Query(value = "",nativeQuery = true)
    public Department findByDepartmentNameIgnoreCase(String departmentName);
    // > (deafult JPQL realized by JPA)

    public List<Department> findByDepartmentNameAndDepartmentCode(String departmentName,String DepartmentCode);


}
