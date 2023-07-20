package com.strupinski.employeeserviceee.service.impl;


import com.strupinski.employeeserviceee.dto.EmployeeDTO;
import com.strupinski.employeeserviceee.dto.converter.EmployeeConverter;
import com.strupinski.employeeserviceee.entity.Employee;
import com.strupinski.employeeserviceee.exception.NoSuchRecordException;
import com.strupinski.employeeserviceee.exception.ServerException;
import com.strupinski.employeeserviceee.repository.DepartmentRepository;
import com.strupinski.employeeserviceee.repository.EmployeeRepository;
import com.strupinski.employeeserviceee.service.EmployeeService;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
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
    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll().stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        return converter.toDTO(employeeRepository.save(converter.toEntity(employeeDTO))
                .orElseThrow(() -> new ServerException("/departments", "error while saving new department")));
    }

    @Override
    public EmployeeDTO findById(Long id) {
        return converter.toDTO(employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchRecordException("employee/", "There is no employee with id " + id)));
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        findAll().forEach(e -> deleteById(e.getId()));
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        Employee existingEmployee = employeeRepository.findById(employeeDTO.getId())
                .orElseThrow(() -> new NoSuchRecordException("employee/", "There is no employee with id " + employeeDTO.getId()));
        existingEmployee.setName(employeeDTO.getName());
        existingEmployee.setSurname(employeeDTO.getSurname());
        existingEmployee.setDepartment(departmentRepository.findById(employeeDTO.getDepartmentId())
                .orElseThrow(() -> new ServerException("/employee", "Error while setting department with id " + employeeDTO.getDepartmentId())));
        existingEmployee.setSalary(employeeDTO.getSalary());

        return converter.toDTO(employeeRepository.save(existingEmployee).orElseThrow());
    }


}
