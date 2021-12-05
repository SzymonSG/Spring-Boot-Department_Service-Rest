package com.scotyCodeBuffer.service;
import com.scotyCodeBuffer.entity.Department;
import com.scotyCodeBuffer.erorr.DepartmentNotFoundException;
import com.scotyCodeBuffer.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DeparmentService{


    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() throws DepartmentNotFoundException {
            List<Department> all = departmentRepository.findAll();
        if (all.isEmpty()){
            throw new DepartmentNotFoundException("List is empty");
        }return all;
    }

    @Override
    public Department fetchById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department=
                departmentRepository.findById(departmentId);
        if (!department.isPresent()){
            throw new DepartmentNotFoundException("Department Not Available");
        }
        return department.get();

    }
    //list and test comments
    @Override
    public Department fetchDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentName(departmentName);

        //if (departament.getDepartmentName().isBlank()){
        //    throw new RuntimeException("Lol");//}
    }

    @Override
    public void deleteById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override /// update maybe better named replace method
    public Department updateDepartment(Long id, Department department) {

        Department depDB = departmentRepository.findById(id).get();


        if (Objects.nonNull(department.getDepartmentName()) &&
                    !"".equalsIgnoreCase(department.getDepartmentName())) // jesli body department nie jest null i nie blank zaktualizuj zanlezione id.
            {
                depDB.setDepartmentName(department.getDepartmentName());
            }

        if (Objects.nonNull(department.getDepartmentAddress()) &&
                    !"".equalsIgnoreCase(department.getDepartmentAddress())) {

                depDB.setDepartmentAddress(department.getDepartmentAddress());
            }

        if (Objects.nonNull(department.getDepartmentCode()) &&
                    !"".equalsIgnoreCase(department.getDepartmentCode())) {
                depDB.setDepartmentCode(department.getDepartmentCode());
            }

        return departmentRepository.save(depDB);

    }
    //this same with filtering multi parameters using streams and Query methods JPA
    @Override
    public List<Department> updatemultiDepartment(String name,
                                                  String code,
                                                  String addresPlace1,
                                                  String addresPlace2,
                                                  Department department) throws DepartmentNotFoundException {

        List<Department> namestoupdate = departmentRepository.findByDepartmentNameAndDepartmentCode(name,code);

        List<Department> filteredList = namestoupdate.stream()
                .filter(x -> x.getDepartmentAddress().equals(addresPlace1)
                        || x.getDepartmentAddress().equals(addresPlace2))
                .collect(Collectors.toList());

        if (filteredList.isEmpty()) {
            throw new DepartmentNotFoundException("List is Empty");
        }
        if (Objects.nonNull(department.getDepartmentName()) &&
                !"".equalsIgnoreCase(department.getDepartmentName()))
            {filteredList.forEach(x -> x.setDepartmentName(department.getDepartmentName()));}

        if (Objects.nonNull(department.getDepartmentAddress()) &&
                    !"".equalsIgnoreCase(department.getDepartmentAddress())) {
                filteredList.forEach(x -> x.setDepartmentAddress(department.getDepartmentAddress()));}

        if (Objects.nonNull(department.getDepartmentCode()) &&
                    !"".equalsIgnoreCase(department.getDepartmentCode())) {
                filteredList.forEach(x -> x.setDepartmentCode(department.getDepartmentCode()));}
        return departmentRepository.saveAll(filteredList);


    }
    //Update with filter and Thorws exceptions if addres don't free
    @Override
    public List<Department> updatemultiDepartmentbyby1(String name,
                                                  String code,
                                                  //String addres,
                                                  Department department) throws DepartmentNotFoundException {
        List<Department> filteredList = departmentRepository.findByDepartmentNameAndDepartmentCode(name,code);
        //List<Department> filteredList = one.stream()
        //        .filter(x -> x.getDepartmentAddress().equals(addres))
        //        .collect(Collectors.toList());
        if (filteredList.isEmpty()) {
            throw new DepartmentNotFoundException("List is Empty");
        }
        boolean free = filteredList.stream()
                .allMatch(x -> x.getDepartmentAddress().equals("free"));
        if (!free) {
            throw new DepartmentNotFoundException("Sorry, this address reserved");
        }
        if (Objects.nonNull(department.getDepartmentName()) &&
                !"".equalsIgnoreCase(department.getDepartmentName()))
        {filteredList.forEach(x -> x.setDepartmentName(department.getDepartmentName()));}

        if (Objects.nonNull(department.getDepartmentAddress()) &&
                !"".equalsIgnoreCase(department.getDepartmentAddress())) {
            filteredList.forEach(x -> x.setDepartmentAddress(department.getDepartmentAddress()));}

        if (Objects.nonNull(department.getDepartmentCode()) &&
                !"".equalsIgnoreCase(department.getDepartmentCode())) {
            filteredList.forEach(x -> x.setDepartmentCode(department.getDepartmentCode()));}
        return departmentRepository.saveAll(filteredList);
    }

}
