package com.scotyCodeBuffer.service;

import com.scotyCodeBuffer.entity.Department;
import com.scotyCodeBuffer.erorr.DepartmentNotFoundException;


import java.util.List;

public interface DeparmentService {

    public Department saveDepartment(Department department);

    public List<Department> fetchDepartmentList() throws DepartmentNotFoundException;

    public Department fetchById(Long departmentId) throws DepartmentNotFoundException;

    public void deleteById(Long departmentId);

    public Department updateDepartment(Long id,Department department);

    public Department fetchDepartmentByName(String departmentName);

    public List<Department> updatemultiDepartment(String name,String code,String addresPlace1,String addresPlace2, Department department) throws DepartmentNotFoundException;

    public List<Department> updatemultiDepartmentbyby1(String name, String code, Department department) throws DepartmentNotFoundException;

}
