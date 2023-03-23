package com.innowise.employeeserviceee.service.impl;


import com.innowise.employeeserviceee.dto.DepartmentDTO;
import com.innowise.employeeserviceee.dto.converter.DepartmentConverter;
import com.innowise.employeeserviceee.entity.Department;
import com.innowise.employeeserviceee.repository.DepartmentRepository;
import com.innowise.employeeserviceee.repository.impl.DepartmentRepositoryImpl;
import com.innowise.employeeserviceee.service.DepartmentService;
import jakarta.ejb.Stateless;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    private DepartmentConverter converter;



    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO department) {
        return converter.toDTO(departmentRepository.save(converter.toEntity(department)));
    }

    @Transactional
    public List<DepartmentDTO> getAll() {
        return departmentRepository.findAll()
                .stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO findById(Long id) {
        Department department = departmentRepository.findById(id)
//                .orElseThrow(() ->
//                        new NoSuchRecordException(String.format("Department with id=%s not found", id))
//                )
                ;
        return converter.toDTO(department);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
//        if (!departmentRepository.existsById(id)) {
//            throw new NoSuchRecordException
//                    (String.format("Department with id=%s not found for deleting", id));
//        }
        departmentRepository.deleteById(id);
    }
}
