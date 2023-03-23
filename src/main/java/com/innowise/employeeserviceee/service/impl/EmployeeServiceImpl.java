package com.innowise.employeeserviceee.service.impl;


import com.innowise.employeeserviceee.dto.EmployeeDTO;
import com.innowise.employeeserviceee.dto.converter.EmployeeConverter;
import com.innowise.employeeserviceee.entity.Employee;
import com.innowise.employeeserviceee.exception.NoSuchRecordException;
import com.innowise.employeeserviceee.repository.EmployeeRepository;
import com.innowise.employeeserviceee.service.EmployeeService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    @EJB
    private EmployeeRepository employeeRepository;
    @EJB
    private EmployeeConverter converter;

    @Transactional
    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream().map(converter::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        return converter.toDTO(employeeRepository.save(converter.toEntity(employeeDTO)));
    }

    @Override
    @Transactional
    public EmployeeDTO findById(Long id) {
        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new NoSuchRecordException(String.format("Employee with id=%s not found", id)))
                ;
        return converter.toDTO(employee);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

}
