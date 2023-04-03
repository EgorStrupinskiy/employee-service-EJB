package com.innowise.employeeserviceee.service.impl;


import com.innowise.employeeserviceee.dto.DepartmentDTO;
import com.innowise.employeeserviceee.dto.converter.DepartmentConverter;
import com.innowise.employeeserviceee.entity.Department;
import com.innowise.employeeserviceee.entity.Employee;
import com.innowise.employeeserviceee.exception.NoSuchRecordException;
import com.innowise.employeeserviceee.repository.DepartmentRepository;
import com.innowise.employeeserviceee.repository.EmployeeRepository;
import com.innowise.employeeserviceee.service.DepartmentService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
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
        return converter.toDTO(departmentRepository.save(converter.toEntity(department)));
    }

    @Transactional
    public List<DepartmentDTO> findAll() {
        return departmentRepository.findAll()
                .stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO findById(Long id) {
        Department department = departmentRepository.findById(id);

        if (Objects.isNull(department)) {
            throw new NoSuchRecordException("department/", format("Employee with id=%s not found", id));
        }
        return converter.toDTO(department);
    }

    @Override
    @Transactional
    public void deleteById(Long id)  {
        if (!departmentRepository.existsById(id)) {
            throw new NoSuchRecordException
                    ("department/", format("Department with id=%s not found for deleting", id));
        }

        departmentRepository.deleteById(id);
    }
}
