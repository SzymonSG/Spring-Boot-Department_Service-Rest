package com.scotyCodeBuffer.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;

//maybe Data
@Getter
@Builder
@Setter
public class DepartmentDto {

    private Long departmentId;
    private String departmentName;
    private String departmentCode;

}
