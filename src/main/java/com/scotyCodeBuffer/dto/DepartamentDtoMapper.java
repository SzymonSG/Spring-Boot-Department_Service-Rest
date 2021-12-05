package com.scotyCodeBuffer.dto;
import com.scotyCodeBuffer.entity.Department;
import java.util.List;
import java.util.stream.Collectors;

public class DepartamentDtoMapper {

    private DepartamentDtoMapper() {
    }

    public static List<DepartmentDto> mapToDtos(List<Department> fetchDepartmentList) {
        List<DepartmentDto> dtoList = fetchDepartmentList.stream()
                .map(department -> convToDto(department))
                .collect(Collectors.toList());
        return dtoList;
    }
    public static DepartmentDto convToDto (Department department) {
        DepartmentDto dto = DepartmentDto.builder()
                .departmentId(department.getDepartmentId())
                .departmentName(department.getDepartmentName())
                .departmentCode(department.getDepartmentCode())
                .build();
        return dto;
    }
}
