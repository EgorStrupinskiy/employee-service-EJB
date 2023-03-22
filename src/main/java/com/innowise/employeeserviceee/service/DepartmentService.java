package com.innowise.employeeserviceee.service;


import com.innowise.employeeserviceee.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    DepartmentDTO saveDepartment(DepartmentDTO department);

    List<DepartmentDTO> getAll();

    DepartmentDTO findById(Long id);

    void deleteById(Long id);
}
