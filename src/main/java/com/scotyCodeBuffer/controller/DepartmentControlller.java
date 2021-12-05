package com.scotyCodeBuffer.controller;
import com.scotyCodeBuffer.dto.DepartamentDtoMapper;
import com.scotyCodeBuffer.dto.DepartmentDto;
import com.scotyCodeBuffer.entity.Department;
import com.scotyCodeBuffer.erorr.DepartmentNotFoundException;
import com.scotyCodeBuffer.service.DeparmentService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class DepartmentControlller {

    @Autowired
    private DeparmentService deparmentService;

    private final Logger LOGGER =
            LoggerFactory.getLogger(DepartmentControlller.class);


    @PostMapping("/departments")
    public Department saveDepartment(@Valid @RequestBody Department department){
        LOGGER.info("Inside saveDepartment of DepartmentController");
        return deparmentService.saveDepartment(department);
    }


    //@Operation(summary ="This is for fetch all the Departments form DB")
    //@ApiResponses(value = {
    //        @ApiResponse(responseCode = "200",
    //        description = "Fetched All the Books form DB",
    //        content = {@Content(mediaType = "application/json")}),
    //        @ApiResponse(responseCode = "404",
    //        description = "Not avilable",
    //        content = @Content)
    //})
    @GetMapping("/departments")
    public List<Department> fetchDepartmentList() throws DepartmentNotFoundException {
        LOGGER.info("Inside fetchDepartmentList of DepartmentController");
        return deparmentService.fetchDepartmentList();
    }

    /// DTO check
    @GetMapping("/dto")
    public List<DepartmentDto> DtoMappingCheck() throws DepartmentNotFoundException {
        return DepartamentDtoMapper.mapToDtos(deparmentService.fetchDepartmentList());
    }

    @GetMapping("/home")
    public String SfetchDepartmentList(){
        return "This is for User Boczu";
    }

    @GetMapping("/departments/{id}")
    public Department fetchById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        return deparmentService.fetchById(departmentId);
    }

    @DeleteMapping("/departments/{id}")
    public String deleteById(@PathVariable("id") Long departmentId){
        deparmentService.deleteById(departmentId);
        return "Deleted sucesfully!";
    }

    @PutMapping("/departments/{id}")
    public Department updateDepartment(@PathVariable("id") Long id, @RequestBody Department department){
        return deparmentService.updateDepartment(id,department);
    }
    @PutMapping("/departments/{name}/{code}/{pl1}/{pl2}")
    public List<Department> updateMultiDepartment(@PathVariable("name") String name,
                                                  @PathVariable("code")String code,
                                                  @PathVariable("pl1") String addresPlace1,
                                                  @PathVariable("pl2") String addresPlace2,
                                                  @RequestBody Department department) throws DepartmentNotFoundException {
        return deparmentService.updatemultiDepartment(name,code,addresPlace1,addresPlace2,department);
    }

    @PutMapping("/departments/up/{name}/{code}")
    public List<Department> updateMultiDepartmentby1(@PathVariable("name") String name,
                                                  @PathVariable("code")String code,
                                                  @RequestBody Department department) throws DepartmentNotFoundException {
        LOGGER.info("Zarezerwowano miejsce");
        return deparmentService.updatemultiDepartmentbyby1(name,code,department);
    }

    @GetMapping("/departments/name/{name}")
    public Department fetchDepartmentByName(@PathVariable("name")String departmentName){
        return deparmentService.fetchDepartmentByName(departmentName);
    }

}
