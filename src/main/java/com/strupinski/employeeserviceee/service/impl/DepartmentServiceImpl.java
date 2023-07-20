package com.strupinski.employeeserviceee.service.impl;


import com.strupinski.employeeserviceee.dto.DepartmentDTO;
import com.strupinski.employeeserviceee.dto.converter.DepartmentConverter;
import com.strupinski.employeeserviceee.exception.NoSuchRecordException;
import com.strupinski.employeeserviceee.exception.ServerException;
import com.strupinski.employeeserviceee.repository.DepartmentRepository;
import com.strupinski.employeeserviceee.repository.EmployeeRepository;
import com.strupinski.employeeserviceee.service.DepartmentService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Stateless
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    @EJB
    private DepartmentRepository departmentRepository;
    @EJB
    private EmployeeRepository employeeRepository;
    @EJB
    private DepartmentConverter converter;


    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO department) {
        return converter.toDTO(departmentRepository.save(converter.toEntity(department))
                .orElseThrow(() -> new ServerException("/departments", "error while saving new department")));
    }

    public List<DepartmentDTO> findAll() {
        return departmentRepository.findAll()
                .stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO findById(Long id) {
        return converter.toDTO(departmentRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchRecordException("department/", format("Employee with id=%s not found", id))));
    }

    @Override
    public void deleteById(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new NoSuchRecordException
                    ("department/", format("Department with id=%s not found for deleting", id));
        }
        departmentRepository.deleteById(id);
    }
}
