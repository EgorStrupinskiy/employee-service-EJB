package com.innowise.employeeserviceee.service.impl;


import com.innowise.employeeserviceee.dto.EmployeeDTO;
import com.innowise.employeeserviceee.dto.converter.EmployeeConverter;
import com.innowise.employeeserviceee.entity.Employee;
import com.innowise.employeeserviceee.exception.NoSuchRecordException;
import com.innowise.employeeserviceee.repository.DepartmentRepository;
import com.innowise.employeeserviceee.repository.EmployeeRepository;
import com.innowise.employeeserviceee.service.EmployeeService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Stateless
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    @EJB
    private EmployeeRepository employeeRepository;
    @EJB
    private DepartmentRepository departmentRepository;
    @EJB
    private EmployeeConverter converter;

    @Override
    @Transactional
    public List<EmployeeDTO> findAll() {
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
        Employee employee = employeeRepository.findById(id);
        if (Objects.isNull(employee)) {
            throw new NoSuchRecordException(String.format("Employee with id=%s not found", id));
        }
        return converter.toDTO(employee);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        Employee existingEmployee = employeeRepository.findById(employeeDTO.getId());
        if (existingEmployee != null) {
            existingEmployee.setName(employeeDTO.getName());
            existingEmployee.setSurname(employeeDTO.getSurname());
            existingEmployee.setDepartment(departmentRepository.findById(employeeDTO.getDepartmentId()));
            existingEmployee.setSalary(employeeDTO.getSalary());

            return converter.toDTO(employeeRepository.save(existingEmployee));
        } else {
            throw new NoSuchRecordException("There is no employee with id" + employeeDTO.getId());
        }
    }


}
