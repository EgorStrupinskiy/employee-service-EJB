package com.innowise.employeeserviceee.service;


import com.innowise.employeeserviceee.dto.EmployeeDTO;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface EmployeeService {
    List<EmployeeDTO> findAll();

    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO findById(Long id);

    void deleteById(Long id);

    void deleteAll();

    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO);
}
