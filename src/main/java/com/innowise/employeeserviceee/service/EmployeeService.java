package com.innowise.employeeserviceee.service;


import com.innowise.employeeserviceee.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO saveEmployee(EmployeeDTO employee);

    EmployeeDTO findById(Long id);


    void deleteById(Long id);
}
